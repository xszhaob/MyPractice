package bo.zhao.practice.leetcode;

public class StringToIntegerAtoi {

    public static int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        String numberStr = str.trim();
        if (numberStr.length() == 0) {
            return 0;
        }

        int sign = 1;
        int result = 0;
        char signC = numberStr.charAt(0);
        if (signC == '-') {
            sign = -1;
            numberStr = numberStr.substring(1);
        } else if (signC == '+') {
            numberStr = numberStr.substring(1);
        }

        for (int i = 0; i < numberStr.length(); i++) {
            char c = numberStr.charAt(i);
            try {
                int n = Integer.parseInt(String.valueOf(c));
                if (result * sign > Integer.MAX_VALUE / 10 || (result * sign == Integer.MAX_VALUE / 10 && n > Integer.MAX_VALUE % 10)) {
                    return Integer.MAX_VALUE;
                }
                if (result * sign < Integer.MIN_VALUE / 10 || (result * sign == Integer.MIN_VALUE / 10 && -n < Integer.MIN_VALUE % 10)) {
                    return Integer.MIN_VALUE;
                }
                result = result * 10 + n;
            } catch (NumberFormatException e) {
                return result * sign;
            }
        }
        return result * sign;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("0-1"));
    }
}
