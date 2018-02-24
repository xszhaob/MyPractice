package bo.zhao.practice.algorithm.chapter01.practice;

import bo.zhao.practice.algorithm.chapter01.LinkedStack;
import org.junit.Assert;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/24
 */
public class Ex1_3_4 {

    private static final char LEFT_PARENTHESIS = '(';
    private static final char RIGHT_PARENTHESIS = ')';
    private static final char LEFT_BRACKET = '[';
    private static final char RIGHT_BRACKET = ']';
    private static final char LEFT_BRACE = '{';
    private static final char RIGHT_BRACE = '}';

    public static boolean isMatch(String parentheses) {
        LinkedStack<Character> leftStack = new LinkedStack<>();

        for (int i = 0; i < parentheses.length(); i++) {
            char c = parentheses.charAt(i);
            if (isLeft(c)) {
                leftStack.push(c);
            } else {
                if (leftStack.isEmpty() || !isMatch(leftStack.pop(), c)) {
                    return false;
                }
            }
        }
        return leftStack.isEmpty();
    }

    private static boolean isMatch(char left, char right) {
        return LEFT_PARENTHESIS == left && RIGHT_PARENTHESIS == right
                || LEFT_BRACKET == left && RIGHT_BRACKET == right
                || LEFT_BRACE == left && RIGHT_BRACE == right;
    }

    private static boolean isLeft(char c) {
        return LEFT_PARENTHESIS == c || LEFT_BRACKET == c || LEFT_BRACE == c;
    }


    public static void main(String[] args) {
        String str1 = "[()]{}{[()()]()}";
        Assert.assertTrue(isMatch(str1));
        String str2 = "[()]{}[()()](){}";
        Assert.assertTrue(isMatch(str2));
    }

}
