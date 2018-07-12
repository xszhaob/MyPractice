package bo.zhao.practice.java8.chapter07;

import junit.framework.Assert;
import org.junit.Test;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by zhaobo on 2018/6/26.
 */
public class Action {

    @Test
    public void test() {
        long costTime = measureSumPerf(Action::ite, 1000000L);
        System.out.println("costTime " + costTime);
        costTime = measureSumPerf(Action::parallelRangedSum, 1000000L);
        System.out.println("costTime " + costTime);
        costTime = measureSumPerf(Action::rangedSum, 1000000L);
        System.out.println("costTime " + costTime);
        costTime = measureSumPerf(Action::sum, 1000000L);
        System.out.println("costTime " + costTime);
    }

    public long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            Assert.assertTrue(500000500000L == sum);
//            System.out.println("sum " + sum);
            long duration = System.nanoTime() - start;
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    private static long ite(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
    }

    private static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }

    private static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }

    private static long sum(long n) {
        long sum = 0;
        for (long i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}
