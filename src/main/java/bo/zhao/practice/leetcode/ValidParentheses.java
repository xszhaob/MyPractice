package bo.zhao.practice.leetcode;

import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        if (s == null || s.length() == 1) {
            return false;
        }
        if (s.isEmpty()) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        stack.push(s.charAt(0));
        for (int j = 1; j < s.length(); j++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(j));
                continue;
            }
            Character peek = stack.peek();
            if (isValid(peek, s.charAt(j))) {
                stack.pop();
            } else {
                stack.push(s.charAt(j));
            }
        }
        return stack.isEmpty();
    }

    private boolean isValid(char c1, char c2) {
        return c1 == '{' && c2 == '}'
                || c1 == '(' && c2 == ')'
                || c1 == '[' && c2 == ']';
    }


    public static void main(String[] args) {
        ValidParentheses parentheses = new ValidParentheses();
        System.out.println(parentheses.isValid("()[]{}"));
    }
}
