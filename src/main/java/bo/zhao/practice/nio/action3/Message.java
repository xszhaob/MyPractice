package bo.zhao.practice.nio.action3;

import java.nio.ByteBuffer;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/8/9
 */
public class Message {
    private MessageBuffer messageBuffer;

    private long socketId = 0;

    private byte[] sharedArray = null;
    private int offset = 0;
    private int capacity;
    private int length = 0;

    private Object metaData = null;

    public Message(MessageBuffer messageBuffer) {
        this.messageBuffer = messageBuffer;
    }

    public int writeToMessage(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();

        // 数据长度 + 剩余长度 > 容量限制
        while (this.length + remaining > capacity) {
            if (!this.messageBuffer.expandMessage(this)) {
                return -1;
            }

        }
        int byteToCopy = Math.min(remaining, this.capacity - this.length);
        byteBuffer.get(this.sharedArray, this.offset + this.length, byteToCopy);
        this.length += byteToCopy;

        return byteToCopy;
    }

    public int writeToMessage(byte[] byteArray) {
        return writeToMessage(byteArray, 0, byteArray.length);
    }

    public int writeToMessage(byte[] byteArray, int offset, int length) {
        int remaining = length;

        while (this.length + remaining > capacity) {
            if (!this.messageBuffer.expandMessage(this)) {
                return -1;
            }
        }

        int byteToCopy = Math.min(remaining, this.capacity - this.length);
        System.arraycopy(byteArray, offset, this.sharedArray, this.offset + this.length, byteToCopy);
        this.length += byteToCopy;
        return byteToCopy;
    }

    /**
     * @param message
     * @param endIndex
     */
    public void writePartialMessageToMessage(Message message, int endIndex) {
        int startIndexOfPartialMessage = message.offset + endIndex;
        int lengthOfPartialMessage = (message.offset + message.length) - endIndex;

        System.arraycopy(message.sharedArray,
                startIndexOfPartialMessage,
                this.sharedArray,
                this.offset,
                lengthOfPartialMessage);
    }

    public MessageBuffer getMessageBuffer() {
        return messageBuffer;
    }

    public void setMessageBuffer(MessageBuffer messageBuffer) {
        this.messageBuffer = messageBuffer;
    }

    public long getSocketId() {
        return socketId;
    }

    public void setSocketId(long socketId) {
        this.socketId = socketId;
    }

    public byte[] getSharedArray() {
        return sharedArray;
    }

    public void setSharedArray(byte[] sharedArray) {
        this.sharedArray = sharedArray;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Object getMetaData() {
        return metaData;
    }

    public void setMetaData(Object metaData) {
        this.metaData = metaData;
    }
}
