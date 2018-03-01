package bo.zhao.practice.algorithm.chapter01.practice;

import bo.zhao.practice.algorithm.chapter01.ResizingArrayDeque;

/**
 * Created by zhaobo on 2018/2/28.
 */
public class Ex1_3_33 {
    public static void main(String[] args) {
        ResizingArrayDeque<String> deque = new ResizingArrayDeque<>();
        deque.pushLeft("a");
        deque.pushLeft("b");
        System.out.println(deque.popLeft());
        deque.pushLeft("c");
        deque.pushLeft("d");
        deque.pushRight("0");
        deque.pushRight("1");
        System.out.println(deque.popLeft());
        System.out.println(deque.popLeft());
        System.out.println(deque.popRight());

        System.out.println(deque);
    }
}
