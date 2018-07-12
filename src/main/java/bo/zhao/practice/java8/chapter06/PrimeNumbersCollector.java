package bo.zhao.practice.java8.chapter06;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * Created by zhaobo on 2018/6/24.
 */
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {
            {
                put(Boolean.TRUE, new ArrayList<>());
                put(Boolean.FALSE, new ArrayList<>());
            }
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) ->
                acc.get(isPrime(acc.get(Boolean.TRUE), candidate)).add(candidate);
    }

    /**
     * 实际上这个收集器是不能并行使用的，因为该算法本身是顺序的。
     * 这意味着永远都不会调用combiner方法，你可以把它的实现留空
     * （更好的做法是抛出一个Unsupported-OperationException异常）。
     * 为了让这个例子完整，我们还是决定实现它。
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> acc1, Map<Boolean, List<Integer>> acc2) -> {
            acc1.get(Boolean.TRUE).addAll(acc2.get(Boolean.TRUE));
            acc1.get(Boolean.FALSE).addAll(acc2.get(Boolean.FALSE));
            return acc1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    /**
     * 就characteristics方法而言，我们已经说过，
     * 它既不是CONCURRENT也不是UNORDERED，但却是IDENTITY_FINISH的。
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    private boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return takeWhile(primes, (i -> i <= candidateRoot))
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }


    private <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A a : list) {
            if (!p.test(a)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }
}
