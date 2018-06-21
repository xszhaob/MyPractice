package bo.zhao.practice.java8.chapter06;

import bo.zhao.practice.java8.BaseAction;
import bo.zhao.practice.java8.chapter05.Dish;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/6/19
 */
public class PartitionInAction extends BaseAction {

    @Test
    public void testAll() {
//        partitionedMenu();
//        vegetarianDishesByType();
//        mostCaloricPartitionedByVegetarian();
        partitionPrimes(1000);
    }

    private void partitionedMenu() {
        Map<Boolean, List<Dish>> partitionedMenu =
                mockMenu()
                        .stream()
                        .collect(Collectors.partitioningBy(Dish::isVegetarian));
        show(partitionedMenu);
    }

    private void vegetarianDishesByType() {
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
                mockMenu()
                        .stream()
                        // 先根据是否为素食做一个分区
                        .collect(Collectors.partitioningBy(Dish::isVegetarian,
                                // 传递第二个收集器，在素食和非素食中分别按照type分类
                                Collectors.groupingBy(Dish::getType)));
        show(vegetarianDishesByType);
    }


    private void mostCaloricPartitionedByVegetarian() {
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                mockMenu()
                        .stream()
                        .collect(Collectors.partitioningBy(Dish::isVegetarian,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparing(Dish::getCalories)),
                                        Optional::get)));

        show(mostCaloricPartitionedByVegetarian);
    }

    private boolean isPrime1(int candidate) {
        return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
    }

    private boolean isPrime2(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }


    private void partitionPrimes(int n) {
        Map<Boolean, List<Integer>> collect = IntStream.range(2, n).boxed().collect(Collectors.partitioningBy(this::isPrime2));
        show(collect);
    }

}
