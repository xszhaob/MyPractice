package bo.zhao.practice.algorithm.chapter03;

import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/19
 */
public class OrderSequentialSearchSTTest extends STBaseTest {

    @Test
    public void test() {
        SortST<String, Integer> st = new OrderSequentialSearchST<>();

        List<Tuple<String, Integer>> tuples = mockList();
        init(tuples, st);

        for (Tuple<String, Integer> tuple : tuples) {
            Assert.assertTrue(st.contains(tuple.getKey()));
            Assert.assertTrue(st.select(st.rank(tuple.getKey())).compareTo(tuple.getKey()) == 0);
        }



        st.delete("C");
        st.delete("E");
        st.delete("A");
        st.delete("M");
        st.delete("S");
        st.delete("H");
        st.delete("L");
        st.delete("X");
        st.delete("R");
        st.delete("P");
        System.out.println(st.size());
        show(st);
    }
}
