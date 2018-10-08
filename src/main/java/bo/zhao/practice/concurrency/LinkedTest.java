package bo.zhao.practice.concurrency;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class LinkedTest {

    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        int threads = 4;
        ExecutorService executor1 = Executors.newFixedThreadPool(threads);
        LinkedList<Integer> queue1 = new SynchronizedLinkedList<>();
        ExecutorService executor2 = Executors.newFixedThreadPool(threads);
        ConcurrentLinkedQueue<Integer> queue2 = new ConcurrentLinkedQueue<>();
        test("synchronizedLinkedList", threads, executor1, queue1);
        test("concurrentLinkedQueue", threads, executor2, queue2);
    }


    private static void test(String desc, int threads, ExecutorService executor, Queue<Integer> queue) throws InterruptedException {
        AtomicLong concurrentLinkedQueueCount = new AtomicLong();
        AtomicBoolean b = new AtomicBoolean(true);
        for (int i = 0; i < threads; i++) {
            executor.submit(() -> {
                while (b.get()) {
                    synchronized (concurrentLinkedQueueCount) {
                        if (queue.isEmpty()) {
                            queue.addAll(list);
                        }
                    }
                    Integer poll = queue.poll();
                    if (poll == null) {
                        continue;
                    }
                    concurrentLinkedQueueCount.incrementAndGet();
                    double pow = Math.pow(poll.doubleValue(), 10);
                    Assert.assertTrue(pow >= 0);
                }
            });
        }
        Thread.sleep(10 * 1000);
        b.compareAndSet(true, false);
        executor.shutdown();
        System.out.println(desc + "\t:" + concurrentLinkedQueueCount.get());
    }
}
