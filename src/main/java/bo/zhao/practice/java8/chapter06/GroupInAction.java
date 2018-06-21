package bo.zhao.practice.java8.chapter06;

import bo.zhao.practice.java8.BaseAction;
import bo.zhao.practice.java8.chapter05.Dish;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by zhaobo on 2018/6/17.
 */
public class GroupInAction extends BaseAction {


    private Collector<Dish, ?, Map<CaloricLevel, List<Dish>>> dishMapCollector = Collectors.groupingBy(
            (Dish dish) -> {
                if (dish.getCalories() <= 400) {
                    return CaloricLevel.DIET;
                } else if (dish.getCalories() <= 700) {
                    return CaloricLevel.NORMAL;
                } else {
                    return CaloricLevel.FAT;
                }
            }
    );

    @Test
    public void testAll() {
//        groupingBy();
//        groupingByCaloric();
//        nestGroupingBy();
//        groupingByAndCounting();
//        mostCaloricByType1();
//        mostCaloricByType2();
//        totalCaloriesByType();
        caloricLevelsByType();
    }


    private void groupingBy() {
        // 普通的单参数groupingBy(f)（其中f是分类函数）实际上是groupingBy(f,toList())的简便写法。
        Map<Dish.Type, List<Dish>> collect = mockMenu().stream().collect(Collectors.groupingBy(Dish::getType));
        show(collect);
    }

    private void groupingByCaloric() {
        Map<CaloricLevel, List<Dish>> collect = mockMenu().stream().collect(dishMapCollector);
        show(collect);
    }

    private void nestGroupingBy() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> collect = mockMenu()
                .stream()
                .collect(Collectors.groupingBy(Dish::getType, dishMapCollector));
        show(collect);
    }


    private void groupingByAndCounting() {
        Map<Dish.Type, Long> groupByAndCounting = mockMenu().stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        show(groupByAndCounting);
    }

    private void mostCaloricByType1() {
        Map<Dish.Type, Optional<Dish>> mostCaloricByType = mockMenu()
                .stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.maxBy(Comparator.comparing(Dish::getCalories))));
        show(mostCaloricByType);
    }

    private void mostCaloricByType2() {
        /*
        对三个子流分别执行这一过程并转换而得到的三个值，
        也就是各个类型中热量最高的Dish，将成为groupingBy
        收集器返回的Map中与各个分类键（Dish的类型）相关联的值。
         */
        Map<Dish.Type, Object> collect = mockMenu()
                .stream()
                /*
                收集器用虚线表示，因此groupingBy是最外层，
                根据菜肴的类型把菜单流分组，得到三个子流。
                 */
                .collect(Collectors.groupingBy(Dish::getType,
                        /*
                        groupingBy收集器包裹着collectingAndThen收集器，
                        因此分组操作得到的每个子流都用这第二个收集器做进一步归约。
                         */
                        Collectors.collectingAndThen(
                                // collectingAndThen收集器又包裹着第三个收集器maxBy。
                                Collectors.maxBy(Comparator.comparing(Dish::getCalories)),
                                /*
                                随后由归约收集器进行子流的归约操作，
                                然后包含它的collectingAndThen收集器会对其
                                结果应用Optional:get转换函数。
                                 */
                                Optional::get)));
        show(collect);
    }

    private void totalCaloriesByType() {
        Map<Dish.Type, Integer> totalCaloriesByType = mockMenu()
                .stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
        show(totalCaloriesByType);
    }

    private void caloricLevelsByType() {
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = mockMenu()
                .stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping((dish) -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 800) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
//                }, Collectors.toSet())));
                    /*
                    但通过使用toCollection，可以有更多的控制。
                    例如，可以传递一个构造函数引用来要求HashSet
                     */
                }, Collectors.toCollection(HashSet::new))));
        show(caloricLevelsByType);
    }




    public enum CaloricLevel {
        DIET, NORMAL, FAT
    }
}
