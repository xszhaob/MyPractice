package bo.zhao.practice.nio.action3.http;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/8/9
 */
public class HttpHeaders {

    public static int HTTP_METHOD_GET = 1;
    public static int HTTP_METHOD_POST = 2;
    public static int HTTP_METHOD_PUT = 3;
    public static int HTTP_METHOD_HEAD = 4;
    public static int HTTP_METHOD_DELETE = 5;

    public int httpMethod = 0;

    public int hostStartIndex = 0;
    public int hostEndIndex = 0;

    public int contentLength = 0;

    public int bodyStartIndex = 0;
    public int bodyEndIndex = 0;
}
