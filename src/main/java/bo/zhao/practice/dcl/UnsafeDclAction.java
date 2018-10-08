package bo.zhao.practice.dcl;

import junit.framework.Assert;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UnsafeDclAction {

    private static int N = 30;

    private static ExecutorService exec = Executors.newFixedThreadPool(N);

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(N);

        for (int i = 0; i < N; i++) {
            exec.submit(() -> {
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                Resource instance = DoubleCheckedLocking.getInstance();
                Assert.assertEquals(12, instance.age);
            });
        }

        exec.shutdown();
    }
}
