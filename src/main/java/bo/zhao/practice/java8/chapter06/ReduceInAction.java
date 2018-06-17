package bo.zhao.practice.java8.chapter06;

import bo.zhao.practice.java8.BaseAction;
import bo.zhao.practice.java8.chapter05.Dish;
import org.junit.Test;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by zhaobo on 2018/6/17.
 */
public class ReduceInAction extends BaseAction {

    @Test
    public void test() {
//        counting();
//        maxBy();
//        summing();
//        averaging();
//        summarizing();
//        joining();
//        totalCalories();
//        mostCalorieDish();
//        sumCalories();
//        totalCalories1();
        totalCalories2();
    }


    private void counting() {
        Long collect = mockMenu().stream().count();
        System.out.println(collect);
    }


    private void maxBy() {
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> collect = mockMenu().stream().collect(Collectors.maxBy(dishComparator));
        if (collect.isPresent()) {
            Dish dish = collect.get();
            System.out.println(dish);
        }
    }

    private void summing() {
        Integer collect = mockMenu().stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(collect);
    }

    private void averaging() {
        Double collect = mockMenu().stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(collect);
    }

    private void summarizing() {
        IntSummaryStatistics collect = mockMenu().stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(collect);
    }


    private void joining() {
        String collect = mockMenu().stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(collect);
    }


    /**
     * 可以用reducing方法创建的收集器来计算你菜单的总热量，它需要三个参数。
     * 第一个参数是归约操作的起始值，也是流中没有元素时的返回值，所以很显然对于数值和而言0是一个合适的值。
     * 第二个参数就是你在6.2.2节中使用的函数，将菜肴转换成一个表示其所含热量的int。
     * 第三个参数是一个BinaryOperator，将两个项目累积成一个同类型的值。这里它就是对两个int求和。
     */
    private void totalCalories() {
        Integer totalCalories = mockMenu().stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println("total calories " + totalCalories);
    }


    private void mostCalorieDish() {
        Optional<Dish> mostCalorieDish = mockMenu().stream().collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        if (mostCalorieDish.isPresent()) {
            System.out.println("most calorie dish is " + mostCalorieDish);
        }
    }

    private void sumCalories() {
        Integer sumCalories = mockMenu().stream().collect(Collectors.reducing(0, Dish::getCalories, (i1, i2) -> i1 + i2));
        System.out.println("sum calories " + sumCalories);
    }

    private void totalCalories1() {
        List<Dish> dishes = mockMenu();
        dishes.clear();
        Integer totalCalories = dishes.stream().map(Dish::getCalories).reduce(Integer::sum).orElse(0);
        System.out.println(totalCalories);
    }

    private void totalCalories2() {
        int totalCalories = mockMenu().stream().mapToInt(Dish::getCalories).sum();
        System.out.println("total calories " + totalCalories);
    }
}
