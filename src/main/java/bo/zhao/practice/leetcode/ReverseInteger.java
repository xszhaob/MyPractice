package bo.zhao.practice.leetcode;

public class ReverseInteger {
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int p = x % 10;
            x = x / 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && p > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && p < Integer.MIN_VALUE % 10)) {
                return 0;
            }
            System.out.println("rev = " + rev + ",p = " + p);
            rev = rev * 10 + p;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-23));
    }
}
