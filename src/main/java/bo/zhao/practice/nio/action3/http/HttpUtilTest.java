package bo.zhao.practice.nio.action3.http;

import junit.framework.Assert;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/8/19
 */
public class HttpUtilTest {

    @Test
    public void testParseHttpRequest() {
        String httpRequest = "GET /HTTP/1.1\r\n\r\n";

        byte[] bytes = httpRequest.getBytes(Charset.forName("utf-8"));
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpUtil.parseHttpRequest(bytes, 0, bytes.length, httpHeaders);

        Assert.assertEquals(0, httpHeaders.contentLength);

    }
}
