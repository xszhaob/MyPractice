package bo.zhao.practice.nio.action3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/7/24
 */
public class MessageWriter {

    private List<Message> writeQueue = new ArrayList<>();

    private Message messageInProcess = null;

    private int byteWritten = 0;

    public MessageWriter() {
    }

    public void enqueue(Message message) {
        if (messageInProcess == null) {
            messageInProcess = message;
        } else {
            writeQueue.add(message);
        }
    }

    public MessageWriter(List<Message> writeQueue, Message messageInProcess, int byteWritted) {
        this.writeQueue = writeQueue;
        this.messageInProcess = messageInProcess;
        this.byteWritten = byteWritted;
    }


    public void write(Socket socket, ByteBuffer byteBuffer) throws IOException {

        byteBuffer.put(this.messageInProcess.getSharedArray(),
                this.messageInProcess.getOffset() + byteWritten,
                this.messageInProcess.getLength() - byteWritten);

        byteBuffer.flip();

        this.byteWritten += socket.write(byteBuffer);
        byteBuffer.clear();

        if (byteWritten >= this.messageInProcess.getLength()) {
            if (this.writeQueue.size() > 0) {
                this.messageInProcess = this.writeQueue.remove(0);
            } else {
                this.messageInProcess = null;
            }
        }
    }


    public boolean isEmpty() {
        return this.messageInProcess == null && this.writeQueue.isEmpty();
    }
}
