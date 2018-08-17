package bo.zhao.practice.nio.action3;

import java.io.IOException;
import java.nio.ByteBuffer;
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

    private Queue<Socket> inboundSocketQueue = null;
    private Queue<Socket> outboundSocketQueue = new LinkedList<>();

    private MessageBuffer readMessageBuffer = null;
    private MessageBuffer writeMessageBuffer = null;

    private IMessageReaderFactory iMessageReaderFactory = null;
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


    @Override
    public void run() {
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


    private void executeCycle() throws IOException {
        // 获取新的socket
        takeNewSockets();
        // 从socket中读取数据
        readFromSockets();
        // write to sockets
        writeToSockets();
    }


    private void takeNewSockets() throws IOException {
        Socket newSocket = this.inboundSocketQueue.poll();

        while (newSocket != null) {
            newSocket.setSocketId(generateNextSocketId());
            newSocket.getSocketChannel().configureBlocking(false);

            newSocket.setMessageReader(this.iMessageReaderFactory.createMessageReader());
            newSocket.getMessageReader().init(this.readMessageBuffer);

            newSocket.setMessageWriter(new MessageWriter());

            this.socketMap.put(newSocket.getSocketId(), newSocket);

            SelectionKey key = newSocket.getSocketChannel().register(this.readSelector, SelectionKey.OP_READ);
            key.attach(newSocket);

            newSocket = this.inboundSocketQueue.poll();
        }
    }


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


    private void writeToSockets() {
        takeNewOutboundMessages();
    }

    private void takeNewOutboundMessages() {
        Message message = this.outboundMessageQueue.poll();

        while (message != null) {
            Socket socket = this.socketMap.get(message.getSocketId());

            if (socket != null) {
                MessageWriter messageWriter = socket.getMessageWriter();
                if (messageWriter.isEmpty()) {

                }
            }
        }


    }

    private long generateNextSocketId() {
        return this.nextSocketId++;
    }
}
