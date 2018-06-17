package bo.zhao.practice.java8.chapter05;

import bo.zhao.practice.java8.BaseAction;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by zhaobo on 2018/6/9.
 */
public class MapInAction extends BaseAction {

    @Test
    public void test() {
//        testMap1();
//        testMap2();
//        testMap3();
//        testFlatMap1();
//        testFlatMap2();
//        testIntStreamRange();
//        testIntStreamRangeClosed();
//        testPythagoreanTriples();
        testQ();
    }

    private void testMap1() {
        List<String> dishNames = mockMenu().stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(dishNames);
    }

    private void testMap2() {
        List<Integer> dishNameLength = mockMenu().stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(dishNameLength);
    }

    private void testMap3() {
        String[] arrayOfWords = {"Hello", "World"};
        Stream<String> stream = Arrays.stream(arrayOfWords);
        List<Stream<String>> collect = stream
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    private void testFlatMap1() {
        String[] arrayOfWords = {"Hello", "World"};
        List<String> distinct = Arrays.stream(arrayOfWords)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinct);
    }

    private void testMapToInt() {
        int sum = mockMenu().stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sum);
    }

    private void testMapToIntToBoxed() {
        Stream<Integer> boxed = mockMenu().stream()
                .mapToInt(Dish::getCalories)
                .boxed();
        System.out.println(boxed);
    }

    private void testIntStreamRangeClosed() {
        long count = IntStream.rangeClosed(1, 100)
                .filter(i -> i % 2 == 0)
                .count();
        System.out.println(count);
    }

    private void testIntStreamRange() {
        long count = IntStream.range(1, 100)
                .filter(i -> i % 2 == 0)
                .count();
        System.out.println(count);
    }


    /**
     * 给定两个数字列表，返回总和能被3整除的数对
     */
    private void testFlatMap2() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        numbers1.stream()
                .flatMap(num1 ->
                        numbers2.stream()
                                .filter(num2 -> (num1 + num2) % 3 == 0)
                                .map(num2 -> new int[]{num1, num2}))
                .forEach(ints -> System.out.println(Arrays.toString(ints)));
    }

    private void testPythagoreanTriples() {
        IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                ).forEach(ints -> System.out.println(Arrays.toString(ints)));
    }

    public void testQ() {
        Stream.iterate(new int[]{0, 1}, ints -> {
            int n1 = ints[0] + ints[1];
            int n2 = ints[1] + n1;
            return new int[]{n1, n2};
        })
                .limit(10)
                .forEach(ints -> System.out.println(Arrays.toString(ints)));
    }
}
