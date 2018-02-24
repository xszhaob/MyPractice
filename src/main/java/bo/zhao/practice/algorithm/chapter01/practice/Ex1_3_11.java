package bo.zhao.practice.algorithm.chapter01.practice;

import bo.zhao.practice.algorithm.chapter01.LinkedStack;

/**
 * Created by zhaobo on 2018/2/24.
 */
public class Ex1_3_11 {

    public static void main(String[] args) {
        String str = "1 3 2 - 4 5 * * +";
        unit(str);
    }

    private static void unit(String postfixStr) {
        LinkedStack<String> vals = new LinkedStack<>();

        String[] split = postfixStr.split(" ");
        for (String s : split) {
            String val;
            switch (s) {
                case "+":
                    val = vals.pop();
                    val = String.valueOf(Integer.parseInt(vals.pop()) + Integer.parseInt(val));
                    break;
                case "-":
                    val = vals.pop();
                    val = String.valueOf(Integer.parseInt(vals.pop()) - Integer.parseInt(val));
                    break;
                case "*":
                    val = vals.pop();
                    val = String.valueOf(Integer.parseInt(vals.pop()) * Integer.parseInt(val));
                    break;
                case "/":
                    val = vals.pop();
                    val = String.valueOf(Integer.parseInt(vals.pop()) / Integer.parseInt(val));
                    break;
                default:
                    val = s;
                    break;
            }
            vals.push(val);
        }

        System.out.println(vals.pop());
    }
}
