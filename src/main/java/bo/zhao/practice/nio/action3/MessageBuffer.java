package bo.zhao.practice.nio.action3;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/7/24
 */
public class MessageBuffer {

    private static final int KB = 1024;
    private static final int MB = 1024 * KB;

    private static final int CAPACITY_SMALL = 4 * KB;
    private static final int CAPACITY_MEDIUM = 128 * KB;
    private static final int CAPACITY_LARGE = 1024 * KB;

    byte[] smallMessageBuffer = new byte[1024 * 4 * KB];
    byte[] mediumMessageBuffer = new byte[128 * 128 * KB];
    byte[] largeMessageBuffer = new byte[16 * 1 * MB];

    QueueIntFlip smallMessageBufferFreeBlocks = new QueueIntFlip(1024);
    QueueIntFlip mediumMessageBufferFreeBlocks = new QueueIntFlip(128);
    QueueIntFlip largeMessageBufferFreeBlocks = new QueueIntFlip(16);


    public MessageBuffer() {
        for (int i = 0; i < smallMessageBuffer.length; i += CAPACITY_SMALL) {
            this.smallMessageBufferFreeBlocks.put(i);
        }
        for (int i = 0; i < mediumMessageBuffer.length; i += CAPACITY_MEDIUM) {
            this.mediumMessageBufferFreeBlocks.put(i);
        }
        for (int i = 0; i < largeMessageBuffer.length; i += CAPACITY_LARGE) {
            this.largeMessageBufferFreeBlocks.put(i);
        }
    }

    public Message getMessage() {
        int nextFreeSmallBlock = this.smallMessageBufferFreeBlocks.take();

        if (nextFreeSmallBlock == -1) {
            return null;
        }

        Message message = new Message(this);
        message.setSharedArray(this.smallMessageBuffer);
        message.setCapacity(CAPACITY_SMALL);
        message.setOffset(nextFreeSmallBlock);
        message.setLength(0);

        return message;
    }

    public boolean expandMessage(Message message) {
        if (message.getCapacity() == CAPACITY_SMALL) {
            return moveMessage(message,
                    this.smallMessageBufferFreeBlocks,
                    this.mediumMessageBufferFreeBlocks,
                    this.mediumMessageBuffer,
                    CAPACITY_MEDIUM);
        } else if (message.getCapacity() == CAPACITY_MEDIUM) {
            return moveMessage(message,
                    this.mediumMessageBufferFreeBlocks,
                    this.largeMessageBufferFreeBlocks,
                    this.largeMessageBuffer,
                    CAPACITY_LARGE);
        } else {
            return false;
        }
    }


    private boolean moveMessage(Message message,
                                QueueIntFlip srcBlockQueue,
                                QueueIntFlip destBlockQueue,
                                byte[] dest, int newCapacity) {
        int nextFreeBlock = destBlockQueue.take();
        if (nextFreeBlock == -1) {
            return false;
        }
        System.arraycopy(message.getSharedArray(),
                message.getOffset(),
                dest,
                nextFreeBlock,
                message.getLength());

        srcBlockQueue.put(message.getOffset());

        message.setSharedArray(dest);
        message.setOffset(nextFreeBlock);
        message.setCapacity(newCapacity);

        return true;
    }
}
