package bo.zhao.practice.algorithm.chapter03;

import java.util.List;

/**
 * Created by zhaobo on 2018/5/17.
 */
public class FrequencyCounter {
    public static void main(String[] args) {
        int minLen = 0;

        List<String> tuples = STBaseTest.mockStringList(1000);

        ST<String, Integer> st = new SequentialSearchST<>();
        int putCount = 0;
        int getCount = 0;
        for (String str : tuples) {
            if (str.length() < minLen) {
                continue;
            }
            if (!st.contains(str)) {
                st.put(str, 1);
            } else {
                getCount++;
                st.put(str, st.get(str) + 1);
            }
            putCount++;
        }

//        String max = "";
//
//        st.put(max, 0);

//        for (String s : st.keys()) {
//            if (st.get(s) > st.get(max)) {
//                max = s;
//            }
//        }

        System.out.println("keys.size()=" + st.size());
//        System.out.println(max + " " + st.get(max));
        System.out.println("put count = " + putCount);
        System.out.println("get count = " + getCount);
    }
}
