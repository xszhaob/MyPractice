package bo.zhao.practice.nio.action3.http;

import bo.zhao.practice.nio.action3.IMessageReader;
import bo.zhao.practice.nio.action3.Message;
import bo.zhao.practice.nio.action3.MessageBuffer;
import bo.zhao.practice.nio.action3.Socket;

import java.nio.ByteBuffer;
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

    private List<Message> complateMessages = new ArrayList<>();

    private Message nextMessage = null;


    @Override
    public void init(MessageBuffer readMessageBuffer) {

    }

    @Override
    public void read(Socket socket, ByteBuffer byteBuffer) {

    }

    @Override
    public List<Message> getMessages() {
        return null;
    }
}
