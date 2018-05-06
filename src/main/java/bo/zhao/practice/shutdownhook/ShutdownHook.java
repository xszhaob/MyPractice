package bo.zhao.practice.shutdownhook;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/29
 */
public class ShutdownHook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("完成销毁。。。")));

        IntStream.range(0, 10).forEach(i -> {
            System.out.println("正在工作。。。。");
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
