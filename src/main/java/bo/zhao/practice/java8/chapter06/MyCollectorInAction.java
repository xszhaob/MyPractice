package bo.zhao.practice.java8.chapter06;

import bo.zhao.practice.java8.BaseAction;
import bo.zhao.practice.java8.chapter05.Dish;
import org.junit.Test;

import java.util.List;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/6/20
 */
public class MyCollectorInAction extends BaseAction {

    @Test
    public void testAll() {
        toListCollector();
    }

    private void toListCollector() {
        List<String> collect = mockMenu().stream().map(Dish::getName).collect(new ToListCollector<>());
        show(collect);
    }
}
