package bo.zhao.practice.java8.chapter04;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/6/5
 */
public class StreamInAction {


    @Test
    public void test() {
//        Assert.assertEquals(action1(), betterAction1());
//        testDistinct();
//        testLimit();
        testSkip();
    }

    private String action1() {
        List<Dish> dishes = mockMenu();

        // 选出低卡路里的食物
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }

        // 根据卡路里对菜肴排序
        lowCaloricDishes.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        List<String> lowCaloricDishesName = new ArrayList<>(lowCaloricDishes.size());
        for (Dish dish : lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());
        }
        return Arrays.toString(lowCaloricDishesName.toArray());
    }

    private String betterAction1() {
        List<Dish> dishes = mockMenu();
        List<String> lowCaloricDishesName = dishes.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        return Arrays.toString(lowCaloricDishesName.toArray());
    }

    private void testDistinct() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 1, 2, 3);
        numbers.stream().distinct().forEach(System.out::println);
    }

    private void testLimit() {
        mockMenu().stream().filter(d -> d.getCalories() > 300).limit(3).forEach(System.out::println);
    }

    private void testSkip() {
        mockMenu().stream().filter(d -> d.getCalories() > 300).skip(3).forEach(System.out::println);
    }

    private List<Dish> mockMenu() {
        return Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }
}
