package bo.zhao.practice.nio.action3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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
    private Queue<Socket> outboundSocketQueue = new LinkedList<>();

    private MessageBuffer readMessageBuffer = null;
    private MessageBuffer writeMessageBuffer = null;

    private IMessageReaderFactory iMessageReaderFactory = null;
    private Map<Long, Socket> socketMap = new HashMap<>();


    @Override
    public void run() {

    }
}
