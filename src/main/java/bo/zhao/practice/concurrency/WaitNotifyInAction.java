package bo.zhao.practice.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/6/21
 */
public class WaitNotifyInAction {

    private static Object object = new Object();

    public static void main(String[] args) {
//        action1();

    }


    private static void action1() {
        Thread thread1 = new Thread(() -> {
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            synchronized (object) {
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了object.notify()");
            }
            System.out.println("线程" + Thread.currentThread().getName() + "释放了锁");
        }, "thread2");

        thread1.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }



    private static void action2() {

    }
}
