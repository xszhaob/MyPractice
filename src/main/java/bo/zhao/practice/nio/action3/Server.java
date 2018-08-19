package bo.zhao.practice.nio.action3;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/7/24
 */
public class Server {

    /**
     * 端口号
     */
    private int port;
    /**
     * 消息读取器工厂
     */
    private IMessageReaderFactory messageFactory;
    /**
     * 消息处理类
     */
    private IMessageProcessor messageProcessor;

    public Server(int port,
                  IMessageReaderFactory messageFactory,
                  IMessageProcessor messageProcessor) {
        this.port = port;
        this.messageFactory = messageFactory;
        this.messageProcessor = messageProcessor;
    }

    public void start() throws IOException {
        Queue<Socket> socketQueue = new ArrayBlockingQueue<>(1024);

        SocketAccepter socketAccepter = new SocketAccepter(port, socketQueue);

        MessageBuffer readBuffer = new MessageBuffer();
        MessageBuffer writeBuffer = new MessageBuffer();
        SocketProcessor socketProcessor = new SocketProcessor(socketQueue, readBuffer, writeBuffer, messageFactory, messageProcessor);

        Thread accepterThread = new Thread(socketAccepter);
        Thread processorThread = new Thread(socketProcessor);

        accepterThread.start();
        processorThread.start();

    }

}
