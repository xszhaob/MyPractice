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
//        baseTestString(tuples, new BinarySearchST<>(tuples.size() / 2));

        BinarySearchST st = new BinarySearchST(tuples.size() / 2);
        for (Tuple<String, Integer> tuple : tuples) {
            st.put(tuple.getKey(), tuple.getValue());
        }
        System.out.println(st.rank("A"));
    }
}
