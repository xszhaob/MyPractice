package bo.zhao.practice.rpc;

import java.util.concurrent.TimeUnit;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/6/29
 */
public class RpcConsumer {
    public static void main(String[] args) {
        HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.out.println(service.hello(String.format("World%d", i)));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
