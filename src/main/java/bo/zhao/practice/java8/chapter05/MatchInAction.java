package bo.zhao.practice.java8.chapter05;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by zhaobo on 2018/6/9.
 */
public class MatchInAction extends BaseAction {

    @Test
    public void test() {
//        testAllMatch();
//        testAnyMatch();
//        testNonMatch();
//        testFindAny();
        testFindFirst();
    }

    private void testAllMatch() {
        boolean b = mockMenu().stream().allMatch(dish -> dish.getCalories() > 100);
        Assert.assertTrue(b);
    }

    private void testAnyMatch() {
        if (mockMenu().stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
    }

    private void testNonMatch() {
        boolean isHealthy = mockMenu().stream().noneMatch(dish -> dish.getCalories() >= 1000);
        Assert.assertTrue(isHealthy);
    }

    private void testFindAny() {
        Optional<Dish> any = mockMenu().stream().filter(Dish::isVegetarian).findAny();
        Assert.assertTrue(any.isPresent());

        mockMenu().stream().filter(Dish::isVegetarian).findAny().ifPresent(System.out::println);
    }

    private void testFindFirst() {
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        someNumbers.stream()
                .map(x -> x * x)
                .filter(y -> y % 3 == 0)
                .findFirst()
                .ifPresent(System.out::println);
    }
}
