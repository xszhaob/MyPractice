package bo.zhao.practice.leetcode;

public class DivideTwoIntegers {
    public static int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        boolean flag = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        long longDividend = Math.abs((long) dividend);
        long longDivisor = Math.abs((long) divisor);
        long step = longDivisor;

        if (longDividend < longDivisor) {
            return 0;
        }

        if (dividend == divisor) {
            return 1;
        }

        long count = 0;
        while (longDividend > longDivisor) {
            longDivisor = longDivisor << 1;
            count++;
        }
        if (longDividend == longDivisor) {
            long result = flag ? 1L << count : -(1L << count);
            return result > Integer.MAX_VALUE || result < Integer.MIN_VALUE ? Integer.MAX_VALUE : (int) result;
        } else {
            longDivisor = longDivisor >> 1;
            long from = 1L << (count - 1);
            long to = 1L << count;
            long i = from + 1;
            for (; i <= to; i++) {
                longDivisor += step;
                if (longDivisor > longDividend) {
                    break;
                }
            }
            long result = flag ? i - 1 : -(i - 1);
            return result > Integer.MAX_VALUE || result < Integer.MIN_VALUE ? Integer.MAX_VALUE : (int) result;
        }
    }


    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(divide(Integer.MIN_VALUE, -1));
    }
}
