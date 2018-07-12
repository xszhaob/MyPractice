package bo.zhao.practice.java8.chapter06;

import bo.zhao.practice.java8.BaseAction;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by zhaobo on 2018/6/24.
 */
public class PartitionPrimesInAction extends BaseAction {

    @Test
    public void testAll() {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimes(1000000);
            long duration = System.nanoTime() - start;

            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");
    }

    private Map<Boolean, List<Integer>> partitionPrimes(int n) {
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(this::isPrime));
//        show(collect.get(Boolean.TRUE));
        return collect;
//        System.out.println("size" + collect.get(Boolean.TRUE).size());
    }

    private boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    private Map<Boolean, List<Integer>> partitionPrimes2(int n) {
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
//        System.out.println("size" + collect.get(Boolean.TRUE).size());
        return collect;
    }


}
