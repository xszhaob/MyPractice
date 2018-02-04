package bo.zhao.practice.algorithm;

import java.util.Stack;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/4
 */
public class Evaluate {

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";
    private static final String SQRT = "sqrt";


    public static void main(String[] args) {
        String val = "( 1 + ( ( 3 - 3 ) * ( 4 * 5 ) ) )";
        evaluate(val);
    }


    private static void evaluate(String str) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> values = new Stack<Double>();

        String[] split = str.split(" ");
        for (String s : split) {
            if ("(".equals(s)) {
                // non op
            } else if (isOperator(s)) {
                ops.push(s);
            } else if (")".equals(s)) {
                values.push(calculate(ops, values));
            } else {
                values.push(Double.parseDouble(s));
            }
        }
        System.out.println(values.pop());
    }


    private static boolean isOperator(String operator) {
        return "+".equals(operator)
                || "-".equals(operator)
                || "*".equals(operator)
                || "/".equals(operator)
                || "sqrt".equals(operator);
    }

    private static double calculate(Stack<String> ops, Stack<Double> vals) {
        String op = ops.pop();
        double v1 = vals.pop();
        if (PLUS.equals(op)) {
            v1 = vals.pop() + v1;
        } else if (MINUS.equals(op)) {
            v1 = vals.pop() - v1;
        } else if (MULTIPLY.equals(op)) {
            v1 = vals.pop() * v1;
        } else if (DIVIDE.equals(op)) {
            v1 = vals.pop() / v1;
        } else if (SQRT.equals(op)) {
            v1 = Math.sqrt(v1);
        }
        System.out.println(v1);
        return v1;
    }
}
