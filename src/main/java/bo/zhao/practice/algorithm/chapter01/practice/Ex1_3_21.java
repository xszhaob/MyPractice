package bo.zhao.practice.algorithm.chapter01.practice;

import bo.zhao.practice.algorithm.chapter01.LinkedBag;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/25
 */
public class Ex1_3_21 {

    public static void main(String[] args) {
        LinkedBag<String> bag = new LinkedBag<>();
        bag.add("a");
        bag.add("b");
        bag.add("c");
        bag.add("d");
        bag.add("e");
        bag.add(null);

        System.out.println(find(bag, null));
    }

    private static boolean find(LinkedBag<String> bag, String key) {
        for (String s : bag) {
            if (key == null && s == null) {
                return true;
            }
            if (key != null && key.equals(s)) {
                return true;
            }
        }

        return false;
    }
}
