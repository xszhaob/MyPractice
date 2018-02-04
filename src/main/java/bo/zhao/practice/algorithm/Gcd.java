package bo.zhao.practice.algorithm;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/25
 */
public class Gcd {
    public static void main(String[] args) {
        System.out.println(gcd(12, 15));
    }


    private static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }

        int r = p % q;
        System.out.println("p=" + p + ",q=" + q + ",r=" + r);
        return gcd(q, r);
    }
}
