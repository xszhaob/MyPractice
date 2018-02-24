package bo.zhao.practice.algorithm.chapter01.practice;

import bo.zhao.practice.algorithm.chapter01.LinkedStack;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/24
 */
public class Parentheses {

    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";
    private static final String LEFT_BRACE = "{";
    private static final String RIGHT_BRACE = "}";

    public static boolean isMatch(String parentheses) {
        String[] split = parentheses.split("");

        LinkedStack<String> leftStack = new LinkedStack<>();

        for (String s : split) {
            if (isLeft(s)) {
                leftStack.push(s);
            } else {
                if (leftStack.isEmpty()) {
                    return false;
                }
                String pop = leftStack.pop();
                if (pop == null || !isMatch(pop, s)) {
                    return false;
                }
            }
        }
        return leftStack.isEmpty();
    }

    private static boolean isMatch(String left, String right) {
        return LEFT_PARENTHESIS.equals(left) && RIGHT_PARENTHESIS.equals(right)
                || LEFT_BRACKET.equals(left) && RIGHT_BRACKET.equals(right)
                || LEFT_BRACE.equals(left) && RIGHT_BRACE.equals(right);
    }

    private static boolean isLeft(String str) {
        return LEFT_PARENTHESIS.equals(str) || LEFT_BRACKET.equals(str) || LEFT_BRACE.equals(str);
    }


    public static void main(String[] args) {
        String str1 = "[()]{}{[()()]()}";
        System.out.println(isMatch(str1));
        String str2 = "[()]{}[()()](){}";
        System.out.println(isMatch(str2));
    }

}
