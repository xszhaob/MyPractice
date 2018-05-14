package bo.zhao.practice.algorithm.chapter03;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/9
 */
public class STBaseTest {

    protected List<Tuple<String, Integer>> mockList() {
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

    private <K, V> List<K> extractKey(List<Tuple<K, V>> tupleList) {
        return tupleList.stream().map(Tuple::getKey).collect(Collectors.toList());
    }

    protected <K, V> void baseTest(List<Tuple<K, V>> tupleList, ST<K, V> st) {
        for (Tuple<K, V> tuple : tupleList) {
            st.put(tuple.getKey(), tuple.getValue());
        }

        testContains(extractKey(tupleList), st);

        show(st);

        testDelete(extractKey(tupleList), st);

        Assert.assertTrue(st.isEmpty());

    }

    private <K, V> void testContains(List<K> keys, ST<K, V> st) {
        keys.forEach(key -> Assert.assertTrue(st.contains(key)));
    }

    private <K, V> void show(ST<K, V> st) {
        Iterator<K> keys = st.keys().iterator();
        while (keys.hasNext()) {
            K key = keys.next();
            System.out.println(key + " " + st.get(key));
        }
    }

    private <K, V> void testDelete(List<K> keys, ST<K, V> st) {
        keys.forEach(st::delete);
    }
}
