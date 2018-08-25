package bo.zhao.practice.nio.action3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/7/24
 */
public class Socket {

    private long socketId;

    private SocketChannel socketChannel = null;

    private IMessageReader messageReader = null;

    private MessageWriter messageWriter = null;

    public boolean endOfStreamReached = false;

    public Socket(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }


    public int read(ByteBuffer byteBuffer) throws IOException {
        // Reads a sequence of bytes from this channel into the given buffer.
        int bytesRead = this.socketChannel.read(byteBuffer);
        int totalBytesRead = bytesRead;

        while (bytesRead > 0) {
            bytesRead = this.socketChannel.read(byteBuffer);
            totalBytesRead += bytesRead;
        }

        // bytesRead等于-1表示管道里的数据已经读完
        if (bytesRead == -1) {
            this.endOfStreamReached = true;
        }

        return totalBytesRead;
    }


    public int write(ByteBuffer byteBuffer) throws IOException {
        int bytesWrite = this.socketChannel.write(byteBuffer);
        int totalBytesWrite = bytesWrite;

        while (bytesWrite > 0 && byteBuffer.hasRemaining()) {
            bytesWrite = this.socketChannel.write(byteBuffer);
            totalBytesWrite += bytesWrite;
        }

        return totalBytesWrite;
    }


    public long getSocketId() {
        return socketId;
    }

    public void setSocketId(long socketId) {
        this.socketId = socketId;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public IMessageReader getMessageReader() {
        return messageReader;
    }

    public void setMessageReader(IMessageReader messageReader) {
        this.messageReader = messageReader;
    }

    public MessageWriter getMessageWriter() {
        return messageWriter;
    }

    public void setMessageWriter(MessageWriter messageWriter) {
        this.messageWriter = messageWriter;
    }

    public boolean isEndOfStreamReached() {
        return endOfStreamReached;
    }

    public void setEndOfStreamReached(boolean endOfStreamReached) {
        this.endOfStreamReached = endOfStreamReached;
    }

    @Override
    public String toString() {
        return "Socket{" +
                "socketId=" + socketId +
                ", socketChannel=" + socketChannel +
                ", messageReader=" + messageReader +
                ", messageWriter=" + messageWriter +
                ", endOfStreamReached=" + endOfStreamReached +
                '}';
    }
}
