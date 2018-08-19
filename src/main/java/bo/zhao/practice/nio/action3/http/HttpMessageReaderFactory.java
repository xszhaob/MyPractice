package bo.zhao.practice.nio.action3.http;

import bo.zhao.practice.nio.action3.IMessageReader;
import bo.zhao.practice.nio.action3.IMessageReaderFactory;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/8/19
 */
public class HttpMessageReaderFactory implements IMessageReaderFactory {

    @Override
    public IMessageReader createMessageReader() {
        return new HttpMessageReader();
    }
}
