package bo.zhao.practice.concurrency;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/6/21
 */
public class ConsumerAndProducer2 {

    private final static int QUEUE_SIZE = 10;

    private PriorityQueue<Integer> queue = new PriorityQueue<>(QUEUE_SIZE);

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
//    private Condition notEmptyCon = lock.newCondition();

    public static void main(String[] args) {
        new ConsumerAndProducer2().start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void start() {
        new Producer().start();
        new Consumer().start();
    }


    public class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("队列为空，等待数据");
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            condition.signal();
                        }
                    }
                    Integer inte = queue.poll();
                    condition.signal();
                    System.out.println(String.format("从队列中删除一个元素%d，队列中剩余%d个元素", inte, queue.size()));
                } finally {
                    lock.unlock();
                }
//                try {
//                    TimeUnit.MILLISECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
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
                lock.lock();
                try {
                    while (queue.size() == QUEUE_SIZE) {
                        System.out.println("队列满，等待消费");
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            condition.signal();
                        }
                    }
                    queue.offer(1);
                    condition.signal();
                    System.out.println(String.format("向队列取中插入一个元素，队列剩余空间：%d", queue.size()));
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
