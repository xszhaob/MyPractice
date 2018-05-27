package bo.zhao.practice.algorithm.chapter03;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/25
 */
public class TestBinarySearchST {
    public static void main(String[] args) {
        String test = "S E A R C H E X A M P L E";
        String[] split = test.split("\\s+");
        int n = split.length;

        BinarySearchST<String, Integer> st = new BinarySearchST<>(2);
        for (int i = 0; i < split.length; i++) {
            st.put(split[i], i);
        }

        System.out.println("size = " + st.size());
        System.out.println("min = " + st.min());
        System.out.println("max = " + st.max());

        // keys
        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }
    }
}
