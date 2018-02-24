package bo.zhao.practice.algorithm.chapter01.practice;

import bo.zhao.practice.algorithm.chapter01.LinkedStack;

/**
 * Created by zhaobo on 2018/2/24.
 */
public class Ex1_3_10 {

    public static void main(String[] args) {
        String str = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        unit(str);
    }

    private static void unit(String str) {
        LinkedStack<String> ops = new LinkedStack<>();
        LinkedStack<String> vals = new LinkedStack<>();

        String[] split = str.split(" ");

        for (String s : split) {
            if (s.equals("(")) {
                continue;
            } else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                ops.push(s);
            } else if (s.equals(")")) {
                String val = vals.pop();
                val = String.format("%s %s %s", vals.pop(), val, ops.pop());
                vals.push(val);
            } else {
                vals.push(s);
            }
        }

        System.out.print(vals.pop());
    }
}
