package bo.zhao.practice.algorithm.chapter03;

import bo.zhao.practice.algorithm.Stopwatch;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhaobo on 2018/5/14.
 */
public class BinarySearchSTTest extends STBaseTest {

    @Test
    public void test() {
        List<Tuple<String, Integer>> tuples = mockMoreList(100000);
        long getCost = 0;
        long putCost = 0;
//        baseTestString(tuples, new BinarySearchST<>(tuples.size() / 2));

        BinarySearchST<String, Integer> st = new BinarySearchST<>(tuples.size() / 2);
        Stopwatch stopwatch = new Stopwatch();
        for (Tuple<String, Integer> tuple : tuples) {
            st.put(tuple.getKey(), tuple.getValue());
        }
        System.out.println("************" + stopwatch.elapsedTime());


        getCost = search(tuples, st, 1000000000);
        putCost = put(tuples, st, 1000000);

        System.out.println("getCost = " + getCost);
        System.out.println("putCost = " + putCost);
    }


    public List<Tuple<String, Integer>> mockMoreList(int count) {
        Random random = new Random();
        List<Tuple<String, Integer>> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(new Tuple<>(String.valueOf(random.nextInt(count)), i));
        }
        return list;
    }


    private long search(List<Tuple<String, Integer>> tuples, ST<String, Integer> st, int count) {
        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            Tuple<String, Integer> tuple = tuples.get(random.nextInt(tuples.size()));
//            System.out.println();
            Assert.assertNotNull(st.get(tuple.getKey()));
        }
        return System.nanoTime() - start;
    }

    private long put(List<Tuple<String, Integer>> tuples, ST<String, Integer> st, int count) {
        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            Tuple<String, Integer> tuple = tuples.get(random.nextInt(tuples.size()));
            st.put(tuple.getKey(), tuple.getValue());
        }
        return System.nanoTime() - start;
    }
}
