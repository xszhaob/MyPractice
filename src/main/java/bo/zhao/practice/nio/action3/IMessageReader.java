package bo.zhao.practice.nio.action3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/7/24
 */
public interface IMessageReader {

    void init(MessageBuffer readMessageBuffer);

    void read(Socket socket, ByteBuffer byteBuffer) throws IOException;

    List<Message> getMessages();
}
