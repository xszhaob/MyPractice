package bo.zhao.practice.algorithm.chapter01.practice;

import bo.zhao.practice.algorithm.chapter01.LinkedStack;

/**
 * Created by zhaobo on 2018/2/24.
 */
public class Ex1_3_12 {
    public static void main(String[] args) {
        LinkedStack<String> origin = new LinkedStack<>();
        origin.push("a");
        origin.push("b");
        origin.push("c");
        origin.push("d");

        LinkedStack<String> copy = copy(origin);
        while (!copy.isEmpty()) {
            System.out.println(copy.pop());
        }
        System.out.println(copy.size());
        System.out.println(origin.size());
    }


    private static LinkedStack<String> copy(LinkedStack<String> origin) {
        LinkedStack<String> tmp = new LinkedStack<>();
        for (String s : origin) {
            tmp.push(s);
        }

        LinkedStack<String> result = new LinkedStack<>();
        for (String s : tmp) {
            result.push(s);
        }
        return result;
    }
}
