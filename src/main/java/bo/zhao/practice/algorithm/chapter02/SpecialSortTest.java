package bo.zhao.practice.algorithm.chapter02;

import org.junit.Assert;

import java.util.Random;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/17
 */
public class SpecialSortTest {
    private static Random random = new Random();

    public static void main(String[] args) {
        int size = 10000;
        long costTime = sort(new Insertion(), size);
        long costTimeSpecial = sortSpecial(new Insertion(), size);
        System.out.println("size = " + size + ", cost time = " + costTime + ", special cost time = " + costTimeSpecial);

    }

    private static long sortSpecial(Sort sort, int n) {
        long total;
        Integer[] a = new Integer[n];
        for (int j = 0; j < n; j++) {
            a[j] = n - j;
        }
        total = sort.sort(a);
        Assert.assertTrue(sort.isSort(a));
        return total;
    }

    private static long sort(Sort sort, int n) {
        long total;
        Integer[] a = new Integer[n];
        for (int j = 0; j < n; j++) {
            a[j] = random.nextInt(n);
        }
        total = sort.sort(a);
        Assert.assertTrue(sort.isSort(a));
        return total;
    }
}
