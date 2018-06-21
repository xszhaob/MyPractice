package bo.zhao.practice.java8;

import bo.zhao.practice.java8.chapter05.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaobo on 2018/6/9.
 */
public class BaseAction {
    protected List<Dish> mockMenu() {
        List<Dish> dishes = new ArrayList<>(9);
        dishes.add(new Dish("pork", false, 800, Dish.Type.MEAT));
        dishes.add(new Dish("beef", false, 700, Dish.Type.MEAT));
        dishes.add(new Dish("chicken", false, 400, Dish.Type.MEAT));
        dishes.add(new Dish("french fries", true, 530, Dish.Type.OTHER));
        dishes.add(new Dish("rice", true, 350, Dish.Type.OTHER));
        dishes.add(new Dish("season fruit", true, 120, Dish.Type.OTHER));
        dishes.add(new Dish("pizza", true, 550, Dish.Type.OTHER));
        dishes.add(new Dish("prawns", false, 300, Dish.Type.FISH));
        dishes.add(new Dish("salmon", false, 450, Dish.Type.FISH));
        return dishes;
    }

    protected void show(Map<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " --- " + entry.getValue());
        }
    }

    protected <T> void show(List<T> list) {
        list.forEach(System.out::println);
    }
}
