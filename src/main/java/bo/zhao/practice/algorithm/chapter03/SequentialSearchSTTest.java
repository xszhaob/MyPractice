package bo.zhao.practice.algorithm.chapter03;

import org.junit.Test;

/**
 * Created by zhaobo on 2018/5/9.
 */
public class SequentialSearchSTTest extends STBaseTest {

    @Test
    public void test() {
        baseTest(mockList(), new SequentialSearchST<>());
    }
}
