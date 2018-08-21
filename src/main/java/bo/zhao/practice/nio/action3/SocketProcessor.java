package bo.zhao.practice.nio.action3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.*;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/7/24
 */
public class SocketProcessor implements Runnable {

    private long nextSocketId = 16 * 1024;

    /**
     * 和accepter共用的队列，accepter把socket放入队列
     */
    private Queue<Socket> inboundSocketQueue;

    private MessageBuffer readMessageBuffer;
    private MessageBuffer writeMessageBuffer;

    private IMessageReaderFactory iMessageReaderFactory;
    private Map<Long, Socket> socketMap = new HashMap<>();

    private ByteBuffer readByteBuffer = ByteBuffer.allocate(1024 * 1024);
    private ByteBuffer writByteBuffer = ByteBuffer.allocate(1024 * 1024);
    private Selector readSelector = null;
    private Selector writeSelector = null;

    private Queue<Message> outboundMessageQueue = new LinkedList<>();

    private IMessageProcessor messageProcessor = null;
    private WriteProxy writeProxy = null;

    private Set<Socket> emptyToNonEmptySocket = new HashSet<>();
    private Set<Socket> nonEmptyToEmptySocket = new HashSet<>();

    public SocketProcessor(Queue<Socket> inboundSocketQueue,
                           MessageBuffer readMessageBuffer,
                           MessageBuffer writeMessageBuffer,
                           IMessageReaderFactory iMessageReaderFactory,
                           IMessageProcessor messageProcessor) throws IOException {
        this.inboundSocketQueue = inboundSocketQueue;

        this.readMessageBuffer = readMessageBuffer;
        this.writeMessageBuffer = writeMessageBuffer;
        this.writeProxy = new WriteProxy(writeMessageBuffer, this.outboundMessageQueue);

        this.iMessageReaderFactory = iMessageReaderFactory;

        this.messageProcessor = messageProcessor;

        this.readSelector = Selector.open();
        this.writeSelector = Selector.open();
    }

    @Override
    public void run() {
        while (true) {
            try {
                executeCycle();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void executeCycle() throws IOException {
        // 获取新的socket
        takeNewSockets();
        // 从socket中读取数据
        readFromSockets();
        writeToSockets();
    }

    /**
     * 拿到新的sock，然后注册到readSelector里面
     */
    private void takeNewSockets() throws IOException {
        Socket newSocket = this.inboundSocketQueue.poll();

        while (newSocket != null) {
            newSocket.setSocketId(generateNextSocketId());
            newSocket.getSocketChannel().configureBlocking(false);

            IMessageReader messageReader = this.iMessageReaderFactory.createMessageReader();
            messageReader.init(this.readMessageBuffer);
            newSocket.setMessageReader(messageReader);

            newSocket.setMessageWriter(new MessageWriter());

            this.socketMap.put(newSocket.getSocketId(), newSocket);

            SelectionKey key = newSocket.getSocketChannel().register(this.readSelector, SelectionKey.OP_READ);
            key.attach(newSocket);

            newSocket = this.inboundSocketQueue.poll();
        }
    }

    /**
     * 查看有哪些已经准备好数据的socket
     */
    private void readFromSockets() throws IOException {
        int readReady = this.readSelector.selectNow();

        if (readReady > 0) {
            Set<SelectionKey> selectionKeys = this.readSelector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                readFromSocket(key);

                iterator.remove();
            }
            selectionKeys.clear();
        }
    }


    private void readFromSocket(SelectionKey key) throws IOException {
        Socket socket = (Socket) key.attachment();

        socket.getMessageReader().read(socket, this.readByteBuffer);

        List<Message> messages = socket.getMessageReader().getMessages();
        if (messages.size() > 0) {
            for (Message message : messages) {
                message.setSocketId(socket.getSocketId());
                this.messageProcessor.process(message, writeProxy);
            }

            messages.clear();
        }

        if (socket.endOfStreamReached) {
            System.out.println(String.format("Socket closed %s", socket.getSocketId()));
            this.socketMap.remove(socket.getSocketId());
            key.attach(null);
            key.cancel();
            key.channel().close();
        }
    }


    private void writeToSockets() throws IOException {
        takeNewOutboundMessages();

        cancelEmptySockets();

        registerNonEmptySockets();

        int writeReady = this.writeSelector.selectNow();
        if (writeReady > 0) {
            Set<SelectionKey> selectionKeys = this.writeSelector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                Socket socket = (Socket) key.attachment();
                socket.getMessageWriter().write(socket, this.writByteBuffer);

                if (socket.getMessageWriter().isEmpty()) {
                    this.nonEmptyToEmptySocket.add(socket);
                }
                iterator.remove();
            }
            selectionKeys.clear();
        }
    }

    private void takeNewOutboundMessages() {
        Message outMessage = this.outboundMessageQueue.poll();

        while (outMessage != null) {
            Socket socket = this.socketMap.get(outMessage.getSocketId());

            if (socket != null) {
                MessageWriter messageWriter = socket.getMessageWriter();
                if (messageWriter.isEmpty()) {
                    messageWriter.enqueue(outMessage);
                    nonEmptyToEmptySocket.remove(socket);
                    emptyToNonEmptySocket.add(socket);
                } else {
                    messageWriter.enqueue(outMessage);
                }
            }

            outMessage = this.outboundMessageQueue.poll();
        }
    }

    private void cancelEmptySockets() {
        for (Socket socket : nonEmptyToEmptySocket) {
            SelectionKey key = socket.getSocketChannel().keyFor(this.writeSelector);
            key.channel();
        }
        nonEmptyToEmptySocket.clear();
    }

    private void registerNonEmptySockets() throws ClosedChannelException {
        for (Socket socket : emptyToNonEmptySocket) {
            socket.getSocketChannel().register(this.writeSelector, SelectionKey.OP_WRITE, socket);
        }
        emptyToNonEmptySocket.clear();
    }

    private long generateNextSocketId() {
        return this.nextSocketId++;
    }
}
