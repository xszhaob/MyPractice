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
        int bytesRead = this.socketChannel.read(byteBuffer);
        int totalBytesRead = bytesRead;

        while (bytesRead > 0) {
            bytesRead = this.socketChannel.read(byteBuffer);
            totalBytesRead += bytesRead;
        }

        if (bytesRead == -1) {
            this.endOfStreamReached = true;
        }

        return totalBytesRead;
    }


    private int write(ByteBuffer byteBuffer) throws IOException {
        int bytesWrite = this.socketChannel.write(byteBuffer);
        int totalBytesWrite = bytesWrite;

        while (bytesWrite > 0 && byteBuffer.hasRemaining()) {
            bytesWrite = this.socketChannel.write(byteBuffer);
            totalBytesWrite += bytesWrite;
        }

        return totalBytesWrite;
    }
}
