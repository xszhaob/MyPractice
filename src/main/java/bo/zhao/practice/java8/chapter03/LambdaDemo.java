package bo.zhao.practice.java8.chapter03;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/27
 */
public class LambdaDemo {


    @Test
    public void testSort() {
        List<Apple> apples = AppleMocker.createApples();
        sort(apples);
        System.out.println(Arrays.toString(apples.toArray()));
    }

    @Test
    public void testSortByAnonymousClass() {
        List<Apple> apples = AppleMocker.createApples();
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                if (o1.getWeight() > o2.getWeight()) {
                    return 1;
                } else if (o1.getWeight() < o2.getWeight()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println(Arrays.toString(apples.toArray()));
    }


    @Test
    public void testSortByLambda() {
        List<Apple> apples = AppleMocker.createApples();
//        apples.sort((o1, o2) -> (o1.getWeight().compareTo(o2.getWeight())));
        apples.sort(Comparator.comparing((o1) -> o1.getWeight()));
        System.out.println(Arrays.toString(apples.toArray()));
    }

    @Test
    public void testSortByMethodReference() {
        List<Apple> apples = AppleMocker.createApples();
        apples.sort((Comparator.comparing(Apple::getWeight)));
        System.out.println(Arrays.toString(apples.toArray()));
    }

    @Test
    public void testSortByCompoundLambda() {
        List<Apple> apples = AppleMocker.createApples();
        // 先按照苹果的重量递增排序，再逆序，如果两个苹果的重量一致，那么再按照颜色排序。
        apples.sort((Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor)));
        System.out.println(Arrays.toString(apples.toArray()));
    }

    @Test
    public void test() {
        Apple apple = new Apple("red", 150);
        Predicate<Apple> redApple = (Apple o) -> "red".equals(o.getColor());
        // 非红色的苹果
        Predicate<Apple> notReadApple = redApple.negate();
        // （红色并且比较重的苹果） 或 （绿色的苹果）
        Predicate<Apple> redAndHeavyAppleOrGreen =
                redApple.and((o1) -> o1.getWeight() > 150)
                .or((o1) -> "green".equals(o1.getColor()));
        System.out.println(redApple.test(apple));
        System.out.println(notReadApple.test(apple));
        apple.setColor("green");
        System.out.println(redAndHeavyAppleOrGreen.test(apple));
    }


    @Test
    public void testFunction() {
        Function<Integer, Integer> f = x -> 2 + x;
        Function<Integer, Integer> g = x -> 4 * x;
        // 类似于g(f(x))，4 * (2 + 1)
        System.out.println(f.andThen(g).apply(1));
        // 类似于f(g(x))，2 + (4 * 1)
        System.out.println(f.compose(g).apply(1));
    }

    private void sort(List<Apple> inventory) {
        inventory.sort(new AppleComparator());
    }


    private static class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple o1, Apple o2) {
            if (o1.getWeight() > o2.getWeight()) {
                return 1;
            } else if (o1.getWeight() < o2.getWeight()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
