package bo.zhao.practice.nio.action3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Queue;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/7/24
 */
public class SocketAccepter implements Runnable {

    private int port;
    private ServerSocketChannel serverSocketChannel;

    private Queue<Socket> socketQueue;

    public SocketAccepter(int port, Queue<Socket> socketQueue) {
        this.port = port;
        this.socketQueue = socketQueue;
    }

    @Override
    public void run() {

        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                SocketChannel accept = serverSocketChannel.accept();
                System.out.println(String.format("socket accept %s", accept.toString()));
                socketQueue.add(new Socket(accept));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
