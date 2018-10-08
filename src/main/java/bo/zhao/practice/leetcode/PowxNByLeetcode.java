package bo.zhao.practice.leetcode;

public class PowxNByLeetcode {

    public double myPow(double x, int n) {
        int p = n;
        if (n < 0) {
            p = -n;
            x = 1 / x;
        }

        return binPow(x, p);
    }

    private double binPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double v = binPow(x, n / 2);
        if (n % 2 == 0) {
            return v * v;
        } else {
            return v * v * x;
        }
    }


    public static void main(String[] args) {
        PowxNByLeetcode leetcode = new PowxNByLeetcode();
        double v = leetcode.myPow(2.00000, -2);
        System.out.println(v);
    }
}
