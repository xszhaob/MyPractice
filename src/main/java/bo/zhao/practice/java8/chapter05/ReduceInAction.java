package bo.zhao.practice.java8.chapter05;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaobo on 2018/6/9.
 */
public class ReduceInAction extends BaseAction {

    @Test
    public void test() {
//        sum();
        menuCount();
    }

    private void sum() {
        List<Integer> someNumbers1 = Arrays.asList(1, 2, 3, 4, 5);
        Integer reduce1 = someNumbers1.stream().reduce(0, (a, b) -> a + b);
        System.out.println(reduce1);
        List<Integer> someNumbers2 = Arrays.asList(1, 2, 3, 4, 5);
        Integer reduce2 = someNumbers2.stream().reduce(0, Integer::sum);
        System.out.println(reduce2);
    }

    private void menuCount() {
        Integer reduce = mockMenu().stream().map(dish -> 1).reduce(0, Integer::sum);
        System.out.println(reduce);
    }
}
