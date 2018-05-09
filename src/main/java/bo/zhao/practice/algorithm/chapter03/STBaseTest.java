package bo.zhao.practice.algorithm.chapter03;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    protected void baseTest(List<Tuple<String, Integer>> tupleList) {
        SequentialSearchST<String, Integer> ssst = new SequentialSearchST<>();
        for (Tuple<String, Integer> tuple : tupleList) {
            ssst.put(tuple.getKey(), tuple.getValue());
        }

        Iterator<String> keys = ssst.keys().iterator();
        while (keys.hasNext()) {
            String s = keys.next();
            System.out.println(s + " " + ssst.get(s));
        }

//        for (String s : ssst.keys()) {
//            System.out.println(s + " " + ssst.get(s));
//        }
    }
}
