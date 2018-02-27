package bo.zhao.practice.leetcode;

import java.math.BigInteger;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/27
 */
public class MultiplyStrings {

    private static String multiply(String num1, String num2) {
        int length1 = num1.length();
        int length2 = num2.length();
        int[] result = new int[length1 + length2];

        for (int i = length1 -1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = length2 - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int p1 = length1 + length2 - i - j - 2;
                int p2 = p1 + 1;

                int mul = n1 * n2 + result[p1];
                result[p1] = mul % 10;
                result[p2] = mul / 10 + result[p2];
            }
        }

        StringBuilder sb = new StringBuilder();
        int n = 0;
        for (int i = result.length - 1; i >= 0; i--) {
            int t = result[i];
            n += t;
            if (n > 0) {
                sb.append(t);
            }
        }
        return  n == 0 ? "0" : sb.toString();
    }


    public static void main(String[] args) {
        String num1 = "990";
        String num2 = "999999999999";

        System.out.println(multiply(num2, num1));
        System.out.println(BigInteger.valueOf(Long.parseLong(num1)).multiply(BigInteger.valueOf(Long.parseLong(num2))));
    }
}
