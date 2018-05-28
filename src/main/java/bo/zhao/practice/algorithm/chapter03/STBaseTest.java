package bo.zhao.practice.algorithm.chapter03;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/9
 */
public abstract class STBaseTest {

    List<Tuple<String, Integer>> tuples;
    protected ST<String, Integer> st;



    @Test
    public void testAll() {
        prepare();
        testSize();
        testKeys();
        testGet();
        testContains();
        testRank();
        testSelect();
        testMin();
        testMax();
        testFloor();
        testCeiling();
        testDeleteMin();
        testDeleteMax();
        testDelete();
    }

    public void prepare() {
        initTupleList();
        initST();
        init(tuples, getSt());
    }

    protected void initTupleList() {
        tuples = mockList();
    }

    protected abstract void initST();

    protected ST<String, Integer> getSt() {
        return st;
    }

    protected int getSize() {
        return 10;
    }


    protected static List<String> mockStringList(int randomN) {
        Random random = new Random();
        List<String> result = new ArrayList<>(randomN);
        for (int i = 0; i < randomN; i++) {
            result.add(String.valueOf(random.nextInt(1000)));
        }
        return result;
    }

    protected static List<Tuple<String, Integer>> mockList() {
        List<Tuple<String, Integer>> result = new ArrayList<>(13);
        result.add(new Tuple<>("S", 0));
        result.add(new Tuple<>("E", 1));
        result.add(new Tuple<>("A", 2));
        result.add(new Tuple<>("R", 3));
        result.add(new Tuple<>("C", 4));
        result.add(new Tuple<>("H", 5));
        result.add(new Tuple<>("E", 6));
        result.add(new Tuple<>("X", 7));
        result.add(new Tuple<>("A", 8));
        result.add(new Tuple<>("M", 9));
        result.add(new Tuple<>("P", 10));
        result.add(new Tuple<>("L", 11));
        result.add(new Tuple<>("E", 12));
        return result;
    }

    protected <K extends Comparable<K>, V> void init(List<Tuple<K, V>> tupleList, ST<K, V> st) {
        for (Tuple<K, V> tuple : tupleList) {
            st.put(tuple.getKey(), tuple.getValue());
        }
    }

    public void testSize() {
        Assert.assertTrue(getSt().size() == getSize());
    }

    public void testKeys() {
        show(getSt());
    }

    public void testGet() {
        List<Tuple<String, Integer>> localTuples = new ArrayList<>(tuples);
        localTuples.add(new Tuple<>("i", 100));
        for (Tuple<String, Integer> localTuple : localTuples) {
            if ("i".equals(localTuple.getKey())) {
                Assert.assertTrue(getSt().get(localTuple.getKey()) == null);
            } else {
                Assert.assertTrue(getSt().get(localTuple.getKey()) != null);
            }
        }
    }

    public void testContains() {
        Assert.assertTrue(getSt().contains("A"));
        Assert.assertTrue(getSt().contains("H"));
        Assert.assertTrue(getSt().contains("S"));
        Assert.assertTrue(getSt().contains("X"));
        Assert.assertTrue(!getSt().contains("D"));
    }

    public void testDelete() {
        try {
            getSt().delete(null);
        } catch (Exception ignore) {}
        for (Tuple<String, Integer> tuple : tuples) {
            getSt().delete(tuple.getKey());
        }
        Assert.assertTrue(getSt().isEmpty());
    }

    protected <K, V> void show(ST<K, V> st) {
        Iterator<K> keys = st.keys().iterator();
        while (keys.hasNext()) {
            K key = keys.next();
            System.out.println(key + " " + st.get(key));
        }
    }

    protected void testRank() {
    }

    protected void testSelect() {
    }

    protected void testMin() {
    }

    protected void testMax() {
    }

    protected void testFloor() {
    }

    protected void testCeiling() {
    }

    public void testDeleteMin() {
    }

    public void testDeleteMax() {
    }
}
