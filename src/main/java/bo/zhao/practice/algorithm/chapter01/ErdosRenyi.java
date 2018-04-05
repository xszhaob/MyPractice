package bo.zhao.practice.algorithm.chapter01;

import java.util.Random;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/5
 */
public class ErdosRenyi {
    private static Random random = new Random();

    public static void main(String[] args) {
        int n = 10;
        for (int i = 0; i < 5; i++) {
            int tmp = n * (int) Math.pow(10, i);
            demo(new QuickFind(tmp), tmp);
        }
        for (int i = 0; i < 5; i++) {
            int tmp = n * (int) Math.pow(10, i);
            demo(new QuickUnion(tmp), tmp);
        }

        for (int i = 0; i < 5; i++) {
            int tmp = n * (int) Math.pow(10, i);
            demo(new WeightedQuickUnion(tmp), tmp);
        }
    }

    private static void demo() {

    }


    private static void demo(UF uf, int n) {
        long start = System.currentTimeMillis();
        int totalCount = 0;
        while (uf.count() > 1) {
            totalCount++;
            int p = random.nextInt(n);
            int q = random.nextInt(n);
            uf.union(p, q);
        }

        System.out.println("n = " + n + ",totalCount = " + totalCount + ", cost " + (System.currentTimeMillis() - start) + " ms");
    }
}