package bo.zhao.practice.algorithm.chapter02;

import bo.zhao.practice.algorithm.chapter02.quicksort.QuickSort;
import org.junit.Assert;

import java.util.Random;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/5
 */
public class SortTest {
    private static Random random = new Random();


    public static void main(String[] args) {
        Integer[] a1 = new Integer[1000001];
        a1[0] = -1;
        for (int i = 1; i <= 1000000; i++) {
            a1[i] = random.nextInt(1000001);
        }
        Integer[] a2 = new Integer[]{9, 8, 2, 3, 4, 1, 6, 7, 6, 1};

        test(a1, new QuickSort());
//        test(a2, new QuickSort());
//        for (int i = 1; i <= 10; i++) {
//            test(100 * i, new Shell());
//        }
    }

    private static void test(int n, Sort sort) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(n);
        }

        test(a, sort);
    }

    private static void test(Integer[] a, Sort sort) {
        long sort1 = sort.sort(a);
        System.out.println("cost " + sort1 + " ms");
//        sort.show(a);
        Assert.assertTrue(sort.isSort(a));
    }
}
