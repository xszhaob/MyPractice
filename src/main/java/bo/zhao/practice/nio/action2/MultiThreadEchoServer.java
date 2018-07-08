package bo.zhao.practice.nio.action2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaobo on 2018/7/8.
 */
public class MultiThreadEchoServer {

    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    static class HandleMsg implements Runnable {

        Socket clientSocket;

        public HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            PrintWriter writer = null;

            try {
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;
                long b = System.currentTimeMillis();

                while ((inputLine = reader.readLine()) != null) {
                    writer.println(inputLine);
                }
                System.out.println(String.format("spend %d ms", (System.currentTimeMillis() - b)));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ServerSocket echoServer;
        Socket clientSocket;

        try {
            echoServer = new ServerSocket(8000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                clientSocket = echoServer.accept();
                System.out.println(String.format("%s connect!", clientSocket.getRemoteSocketAddress()));
                executor.execute(new HandleMsg(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
