package bo.zhao.practice.algorithm.chapter01.practice;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;
import bo.zhao.practice.algorithm.chapter01.LinkedStack;

/**
 * Created by zhaobo on 2018/2/24.
 * 利用栈数据结构实现字符串反转
 */
public class Ex1_3_6 {

    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");

        LinkedQueue<String> revers = revers(queue);
        while (!revers.isEmpty()) {
            System.out.println(revers.dequeue());
        }
    }

    private static LinkedQueue<String> revers(LinkedQueue<String> queue) {
        LinkedStack<String> stack = new LinkedStack<>();

        while (!queue.isEmpty()) {
            stack.push(queue.dequeue());
        }

        while (!stack.isEmpty()) {
            queue.enqueue(stack.pop());
        }

        return queue;
    }
}
