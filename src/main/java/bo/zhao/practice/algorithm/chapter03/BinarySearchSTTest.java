package bo.zhao.practice.algorithm.chapter03;

import org.junit.Test;

import java.util.List;

/**
 * Created by zhaobo on 2018/5/14.
 */
public class BinarySearchSTTest extends STBaseTest {

    @Test
    public void test() {
        List<Tuple<String, Integer>> tuples = mockList();
        baseTest(tuples, new BinarySearchST<>(tuples.size() / 2));
    }
}
