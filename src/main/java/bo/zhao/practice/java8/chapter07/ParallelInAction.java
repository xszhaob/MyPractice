package bo.zhao.practice.java8.chapter07;

import org.junit.Test;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/6/26
 */
public class ParallelInAction {

    @Test
    public void testAll() {
        long sequentialSum = measureSumPerf(ParallelInAction::sequentialSum, 1000000);
        System.out.println("sequentialSum fastest -> " + sequentialSum);
        long iterativeSum = measureSumPerf(ParallelInAction::iterativeSum, 1000000);
        System.out.println("iterativeSum fastest -> " + iterativeSum);
        long parallelSum = measureSumPerf(ParallelInAction::parallelSum, 1000000);
        System.out.println("parallelSum fastest -> " + parallelSum);
    }

    private long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (long i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            adder.apply(n);
            long duration = System.currentTimeMillis() - start;
            fastest = fastest > duration ? duration : fastest;
        }
        return fastest;
    }


    private static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
    }

    private static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }

    private static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + i).limit(n).parallel().reduce(0L, Long::sum);
    }
}
