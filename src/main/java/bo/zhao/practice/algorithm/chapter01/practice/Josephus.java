package bo.zhao.practice.algorithm.chapter01.practice;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;

/**
 * Created by zhaobo on 2018/3/22.
 */
public class Josephus {

    public static void main(String[] args) {
        demo(7, 2);
    }


    private static void demo(int count, int killM) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        for (int i = 0; i < count; i++) {
            queue.enqueue(i);
        }

        while (!queue.isEmpty()) {
            for (int i = 0; i < killM - 1; i++) {
                queue.enqueue(queue.dequeue());
            }
            System.out.println(queue.dequeue());
        }
    }
}
