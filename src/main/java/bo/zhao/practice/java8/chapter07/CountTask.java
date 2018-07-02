package bo.zhao.practice.java8.chapter07;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by zhaobo on 2018/7/2.
 */
public class CountTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 1000;

    private long start;

    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            long step = (end - start) / 100;
            ArrayList<CountTask> tasks = new ArrayList<>(100);
            long pos = start;
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
                long lastOne = pos + step;
                if (lastOne > end) {
                    lastOne = end;
                }
                CountTask task = new CountTask(pos, lastOne);
                task.fork();
                tasks.add(task);
                pos = lastOne + 1;
            }
            for (CountTask task : tasks) {
                try {
                    sum += task.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        long sum = 0;
        for (int i = 10000; i <= 100000; i++) {
            sum += i;
        }
        ForkJoinPool pool = new ForkJoinPool();
        Long invoke = pool.invoke(new CountTask(10000, 100000));
        Assert.assertTrue(sum == invoke);
    }
}
