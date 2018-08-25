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
        // 从socket中把数据读取到byteBuffer中
        socket.read(byteBuffer);
        println(byteBuffer);
        // 准备读
        byteBuffer.flip();

        // 如果当前没有剩余数据，直接返回
        if (byteBuffer.remaining() == 0) {
            byteBuffer.clear();
            return;
        }

        // 把数据从byteBuffer写入到message
        this.nextMessage.writeToMessage(byteBuffer);

        // 解析message中的数据
        int endIndex = HttpUtil.parseHttpRequest(this.nextMessage.getSharedArray(),
                this.nextMessage.getOffset(),
                this.nextMessage.getOffset() + this.nextMessage.getLength(),
                (HttpHeaders) this.nextMessage.getMetaData());

        if (endIndex != -1) {
            Message message = this.messageBuffer.getMessage();
            message.setMetaData(new HttpHeaders());
            message.writePartialMessageToMessage(nextMessage, endIndex);

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
        System.out.println("*****************");
        byteBuffer.flip();
        String encoding = System.getProperty("file.encoding");
        System.out.println(Charset.forName(encoding).decode(byteBuffer));
        System.out.println("*****************");
    }
}
