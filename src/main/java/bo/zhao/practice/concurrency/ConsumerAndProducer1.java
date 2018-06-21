package bo.zhao.practice.concurrency;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/6/21
 */
public class ConsumerAndProducer1 {
    private int queueSize = 1000;

    private final PriorityQueue<Integer> queue = new PriorityQueue<>();

    public static void main(String[] args) {
        new ConsumerAndProducer1().start();
    }

    public void start() {
        Consumer consumer = new Consumer();
        Producer producer = new Producer();
        producer.start();
        consumer.start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }


    public class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }


        private void consume() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    Integer inte = queue.poll();
                    queue.notify();
                    System.out.println(String.format("从队列中删除一个元素%d，队列中剩余%d个元素", inte, queue.size()));
                }
            }
        }
    }


    public class Producer extends Thread {
        @Override
        public void run() {
            produce();
        }


        private void produce() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == queueSize) {
                        System.out.println("队列满，等待有剩余空间");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.offer(1);
                    queue.notify();
                    System.out.println(String.format("向队列取中插入一个元素，队列剩余空间：%d", queue.size()));
                }
            }
        }
    }

}
