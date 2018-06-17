package bo.zhao.practice.java8.chapter06;

import bo.zhao.practice.java8.BaseAction;
import bo.zhao.practice.java8.chapter05.Dish;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by zhaobo on 2018/6/17.
 */
public class GroupInAction extends BaseAction {


    private Collector<Dish, ?, Map<CaloricLevel, List<Dish>>> dishMapCollector = Collectors.groupingBy(
            (Dish dish) -> {
                if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                else return CaloricLevel.FAT;
            }
    );

    @Test
    public void testAll() {
//        groupingBy();
//        groupingByCaloric();
        nestGroupingBy();
    }


    private void groupingBy() {
        Map<Dish.Type, List<Dish>> collect = mockMenu().stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(collect);
    }

    private void groupingByCaloric() {

        Map<CaloricLevel, List<Dish>> collect = mockMenu().stream().collect(dishMapCollector);

        for (Map.Entry<CaloricLevel, List<Dish>> entry : collect.entrySet()) {
            System.out.println(entry.getKey() + " --- " + entry.getValue());
        }
    }

    private void nestGroupingBy() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> collect = mockMenu()
                .stream()
                .collect(Collectors.groupingBy(Dish::getType, dishMapCollector));
        for (Map.Entry<Dish.Type, Map<CaloricLevel, List<Dish>>> entry : collect.entrySet()) {
            System.out.println(entry.getKey() + " --- " + entry.getValue());
        }
    }


    public enum CaloricLevel {
        DIET, NORMAL, FAT
    }
}
