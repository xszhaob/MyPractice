package bo.zhao.practice.nio.action3.http;

import bo.zhao.practice.nio.action3.IMessageReader;
import bo.zhao.practice.nio.action3.Message;
import bo.zhao.practice.nio.action3.MessageBuffer;
import bo.zhao.practice.nio.action3.Socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/8/9
 */
public class HttpMessageReader implements IMessageReader {
    private MessageBuffer messageBuffer = null;

    private List<Message> completeMessages = new ArrayList<>();

    private Message nextMessage = null;


    @Override
    public void init(MessageBuffer readMessageBuffer) {
        this.messageBuffer = readMessageBuffer;
        this.nextMessage = messageBuffer.getMessage();
        this.nextMessage.setMetaData(new HttpHeaders());
    }

    @Override
    public void read(Socket socket, ByteBuffer byteBuffer) throws IOException {
        int read = socket.read(byteBuffer);
        System.out.println("*****************");
        println(byteBuffer);
        System.out.println("*****************");
        // 准备读
        byteBuffer.flip();

        // 如果当前没有剩余数据，直接返回
        if (byteBuffer.remaining() == 0) {
            byteBuffer.clear();
            return;
        }

        this.nextMessage.writeToMessage(byteBuffer);

        int endIndex = HttpUtil.parseHttpRequest(this.nextMessage.getSharedArray(),
                this.nextMessage.getOffset(),
                this.nextMessage.getOffset() + this.nextMessage.getLength(),
                (HttpHeaders) this.nextMessage.getMetaData());

        if (endIndex != -1) {
            Message message = this.messageBuffer.getMessage();
            message.setMetaData(new HttpHeaders());
            message.writePartialMessageToMessage(message, endIndex);

            completeMessages.add(nextMessage);
            nextMessage = message;
        }
        byteBuffer.clear();
    }

    @Override
    public List<Message> getMessages() {
        return this.completeMessages;
    }

    private void println(ByteBuffer byteBuffer) {
        byteBuffer.flip();
        String property = System.getProperty("file.encoding");
        System.out.println(Charset.forName(property).decode(byteBuffer));
    }
}
