package bo.zhao.practice.java8.chapter07;

import junit.framework.Assert;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Created by zhaobo on 2018/6/27.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;

    private final int start;

    private final int end;

    private static final long THRESHOLD = 10000;


    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        // 如果大小小于等于阈值，顺序计算结果
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        // 创建一个子任务来为数组的前一半求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        // 利用另一个ForkJoinPool线程异步执行新创建的子任务
        leftTask.fork();
        // 创建一个子任务为数组的后一半求和
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        // 同步执行第二个子任务，有可能允许进一步递归划分任务
        Long rightResult = rightTask.compute();
        // 读取第一个子任务的结果，如果尚未完成就等待
        Long leftResult = leftTask.join();
        // 该任务的结果是两个子任务结果的组合
        return leftResult + rightResult;
    }


    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    private static long forkJoinSum(long n) {
        long[] longs = LongStream.rangeClosed(1, n).toArray();
        ForkJoinSumCalculator calculator = new ForkJoinSumCalculator(longs);
        return new ForkJoinPool().invoke(calculator);
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long sum = forkJoinSum(1000000L);
        long costTime = System.currentTimeMillis() - start;
        System.out.println("cost time " + costTime);
        Assert.assertTrue(500000500000L == sum);
    }
}
