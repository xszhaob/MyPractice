package bo.zhao.practice.algorithm.chapter01.practice;

import bo.zhao.practice.algorithm.chapter01.LinkedStack;

/**
 * Created by zhaobo on 2018/2/24.
 */
public class Ex1_3_9 {
    public static void main(String[] args) {
        String demo = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        demo(demo);
    }


    private static void demo(String str) {
        LinkedStack<String> ops = new LinkedStack<>();
        LinkedStack<String> vals = new LinkedStack<>();

        String[] split = str.split(" ");
        for (String s : split) {
            if ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s)) {
                ops.push(s);
            } else if (")".equals(s)) {
                String op = ops.pop();
                String val = vals.pop();

                if ("+".equals(op) || "-".equals(op) || "*".equals(op) || "/".equals(op)) {
                    val = String.format("( %s %s %s )", vals.pop(), op, val);
                }
                System.out.println("val->" + val);
                vals.push(val);
            } else if (!"(".equals(s)){
                vals.push(s);
            }
        }

        while (!vals.isEmpty()) {
            System.out.print(vals.pop());
        }


    }
}
