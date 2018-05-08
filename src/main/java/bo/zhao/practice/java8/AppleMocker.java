package bo.zhao.practice.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/6
 */
public class AppleMocker {

    public static List<Apple> createApples() {
        Apple apple1 = new Apple("green", 151);
        Apple apple2 = new Apple("green", 149);
        Apple apple3 = new Apple("green", 129);
        Apple apple4 = new Apple("green", 200);
        List<Apple> apples = new ArrayList<>();
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        apples.add(apple4);
        return apples;
    }
}
