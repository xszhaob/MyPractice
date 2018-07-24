package bo.zhao.practice.nio.action3;

import java.util.Queue;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/7/24
 */
public class SocketProcessor implements Runnable {

    private Queue<Socket> inboundSocketQueue = null;

    private MessageBuffer messageBuffer = null;


    @Override
    public void run() {

    }
}
