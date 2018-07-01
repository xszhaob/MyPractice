package bo.zhao.practice.java8.chapter07;

import junit.framework.Assert;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 这里用了一个LongStream来生成包含前n个自然数的数组，
 * 然后创建一个ForkJoinTask（RecursiveTask的父类），
 * 并把数组传递给代码清单7-2所示ForkJoinSumCalculator的公共构造函数。
 * 最后，你创建了一个新的ForkJoinPool，并把任务传给它的调用方法 。
 * 在ForkJoinPool中执行时，最后一个方法返回的值就是
 * ForkJoinSumCalculator类定义的任务结果。
 * 请注意在实际应用时，使用多个ForkJoinPool是没有什么意义的。
 * 正是出于这个原因，一般来说把它实例化一次，然后把实例保存在静态字段中，
 * 使之成为单例，这样就可以在软件中任何部分方便地重用了。
 * 这里创建时用了其默认的无参数构造函数，
 * 这意味着想让线程池使用JVM能够使用的所有处理器。
 * 更确切地说，该构造函数将使用Runtime.availableProcessors的
 * 返回值来决定线程池使用的线程数。
 * 请注意availableProcessors方法虽然看起来是处理器，
 * 但它实际上返回的是可用内核的数量，包括超线程生成的虚拟内核。
 * 运行ForkJoinSumCalculator当把ForkJoinSumCalculator任务传给ForkJoinPool时，
 * 这个任务就由池中的一个线程执行，这个线程会调用任务的compute方法。
 * 该方法会检查任务是否小到足以顺序执行，
 * 如果不够小则会把要求和的数组分成两半，
 * 分给两个新的ForkJoinSumCalculator，而它们也由ForkJoinPool安排执行。
 * 因此，这一过程可以递归重复，把原任务分为更小的任务，
 * 直到满足不方便或不可能再进一步拆分的条件（本例中是求和的项目数小于等于10000）。
 * 这时会顺序计算每个任务的结果，然后由分支过程创建的（隐含的）
 * 任务二叉树遍历回到它的根。接下来会合并每个子任务的
 * 部分结果，从而得到总任务的结果。
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
