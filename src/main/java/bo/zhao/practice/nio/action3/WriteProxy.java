package bo.zhao.practice.nio.action3;

import java.util.Queue;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/8/15
 */
public class WriteProxy {

    private MessageBuffer messageBuffer;

    private Queue<Message> writeQueue;

    public WriteProxy(MessageBuffer messageBuffer, Queue<Message> writeQueue) {
        this.messageBuffer = messageBuffer;
        this.writeQueue = writeQueue;
    }

    public Message getMessage() {
        return messageBuffer.getMessage();
    }

    public void enqueue(Message message) {
        writeQueue.offer(message);
    }
}
