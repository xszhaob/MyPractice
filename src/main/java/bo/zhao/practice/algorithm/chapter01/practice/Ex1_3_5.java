package bo.zhao.practice.algorithm.chapter01.practice;

import bo.zhao.practice.algorithm.chapter01.LinkedStack;

/**
 * Created by zhaobo on 2018/2/24.
 */
public class Ex1_3_5 {

    public static void main(String[] args) {

        print(4);
        System.out.println();

        System.out.println(Integer.toBinaryString(4));

    }

    public static void print(int n) {
        LinkedStack<Integer> stack = new LinkedStack<>();

        while (n > 0) {
            stack.push(n % 2);
            n = n / 2;
        }

        for (Integer integer : stack) {
            System.out.print(integer + " ");
        }
    }
}
