package bo.zhao.practice.nio.action3;

import bo.zhao.practice.nio.action3.http.HttpMessageReaderFactory;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/8/19
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String httpResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Length: 38\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><body>Hello World!</body></html>";

        byte[] httpResponseBytes = httpResponse.getBytes(Charset.forName("utf-8"));

        IMessageProcessor messageProcessor = (request, writeProxy) -> {
            System.out.println("Message Received from socket: " + request.getSocketId());

            Message response = writeProxy.getMessage();
            response.setSocketId(request.getSocketId());
            response.writeToMessage(httpResponseBytes);

            writeProxy.enqueue(response);
        };
        Server server = new Server(9999, new HttpMessageReaderFactory(), messageProcessor);
        server.start();

    }


}
