package bo.zhao.practice.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/6
 */
public class AppleUtils {


    private static <T> List<T> filter(List<T> inventory, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : inventory) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private static <T> void prettyPrint(List<T> inventory, Formatter<T> appleFormatter) {
        for (T t : inventory) {
            String output = appleFormatter.accept(t);
            System.out.println(output);
        }
    }


    public static void main(String[] args) {
        List<Apple> apples = AppleMocker.createApples();

        prettyPrint(apples, new AppleFancyFormatter());
        prettyPrint(apples, new AppleSimpleFormatter());

        List<Apple> filterApples = filter(apples, apple -> "green".equals(apple.getColor()));
        System.out.println(Arrays.toString(filterApples.toArray()));

        List<Integer> integers = new ArrayList<>(5);
        integers.add(1);
        integers.add(3);
        integers.add(5);
        integers.add(7);
        integers.add(9);

        List<Integer> filterInt = filter(integers, n -> n > 4);
        System.out.println(Arrays.toString(filterInt.toArray()));

        apples.sort((apple1, apple2) -> apple1.getWeight() - apple2.getWeight());
        prettyPrint(apples, new AppleSimpleFormatter());


        new Thread(() -> System.out.println("hello world")).start();
    }
}
