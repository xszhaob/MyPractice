package bo.zhao.practice.java8.chapter06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/6/20
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * 建立新的可变的结果容器。
     * supplier方法必须返回一个结果为空的Supplier，
     * 也就是一个无参数函数，在调用时它会创建一个空的累加器实例，
     * 供数据收集过程使用。
     * 很明显，对于将累加器本身作为结果返回的收集器，
     * 比如我们的ToListCollector，在对空流执行操作的时候，
     * 这个空的累加器也代表了收集过程的结果。
     */
    @Override
    public Supplier<List<T>> supplier() {
        /*return new Supplier<List<T>>() {
            @Override
            public List<T> get() {
                return new ArrayList<>();
            }
        };*/
        return ArrayList::new;
    }

    /**
     * accumulator方法会返回执行归约操作的函数，
     * 这个方法把元素保存到结果容器中。
     * 当遍历到流中第n个元素时，这个函数执行时会有两个参数：
     * 保存归约结果的累加器（已收集了流中的前 n-1 个项目），
     * 还有第n个元素本身。
     * 该函数将返回void，因为累加器是原位更新，
     * 即函数的执行改变了它的内部状态以体现遍历的元素的效果。
     * 对于ToListCollector，这个函数仅仅会把当前项目添加至已经遍历过的项目的列表。
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        /*return new BiConsumer<List<T>, T>() {
            @Override
            public void accept(List<T> list, T t) {
                list.add(t);
            }
        };*/
        return List::add;
    }

    /**
     * 接收两个结果容器并合两个结果。
     * 四个方法中的最后一个——combiner方法会返回一个供归约操作使用的函数，
     * 它定义了对流的各个子部分进行并行处理时，
     * 各个子部分归约所得的累加器要如何合并。
     * 对于toList而言，这个方法的实现非常简单，
     * 只要把从流的第二个部分收集到的项目列表加到遍历
     * 第一部分时得到的列表后面就行了。
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        /*return new BinaryOperator<List<T>>() {
            @Override
            public List<T> apply(List<T> list1, List<T> list2) {
                list1.addAll(list2);
                return list1;
            }
        };*/
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 把数据从中间的累积类型转换为最终的结果类型。
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     * 定义收集器的行为。包括流是否可以并行规约，可以使用哪些优化等。
     * IDENTITY_FINISH--表明完成函数返回函数式接口的函数是一个恒等式函数，可以跳过。
     * 这种情况下，累加器对象将会直接用作归约过程的最终结果。
     * 这也意味着，将累加器A不加检查地转换为结果R是安全的。
     * CONCURRENT--accumulator函数可以从多个线程同时调用，且该收集器可以并行归约流。
     * 如果收集器没有标为UNORDERED，那它仅在用于无序数据源时才可以并行归约。
     * UNORDERED--归约结果不受流中项目的遍历和累积顺序的影响。
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.IDENTITY_FINISH,
                Characteristics.CONCURRENT));
    }
}
