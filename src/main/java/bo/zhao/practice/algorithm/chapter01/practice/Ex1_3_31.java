package bo.zhao.practice.algorithm.chapter01.practice;

import bo.zhao.practice.algorithm.chapter01.DoubleLinkedList;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/25
 */
public class Ex1_3_31 {
    public static void main(String[] args) {
        DoubleLinkedList<String> linked = new DoubleLinkedList<>();
        linked.addFirst("c");
        linked.addFirst("b");
        linked.addLast("d");
        linked.addLast("e");
        linked.addBefore("b", "a");
        linked.addAfter("e", "f");

        System.out.println(linked.remove("d"));
        System.out.println(linked);
        System.out.println(linked.toReversedString());

        System.out.println(linked.pollFirst());
        System.out.println(linked.pollLast());
        System.out.println(linked);
        System.out.println(linked.toReversedString());
    }

}
