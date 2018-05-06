package bo.zhao.practice.algorithm.chapter02;

import bo.zhao.practice.algorithm.chapter02.quicksort.Quick3way;
import bo.zhao.practice.algorithm.chapter02.quicksort.QuickSort;
import org.junit.Assert;

import java.util.Random;

/**
 * 文件描述：联系2.1.31
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/17
 */
public class SortDoubleTest {

    private static final int INIT_LENGTH = 1000;
    private static Random random = new Random();

    public static void main(String[] args) {
        test(new Quick3way());
        System.out.println("*******************");
        test(new QuickSort());
    }

    private static void test(Sort sort) {
        long lastCostTime = 0;
        for (int i = 0; i < 10; i++) {
            int size = INIT_LENGTH * (int) Math.pow(2, i);
            long costTime = sort(sort, size);
            if (lastCostTime != 0) {
                System.out.println("log2" + MergeTest.log(2, size));
                System.out.println("compare count = " + sort.comCount + ",size = " + size + ", cost time = " + costTime + ", lastCostTime / costTime = " + (((double) costTime) / lastCostTime));
            } else {
                System.out.println("log2" + MergeTest.log(2, size));
                System.out.println("compare count = " + sort.comCount + ",size = " + size + ", cost time = " + costTime);
            }
            lastCostTime = costTime;
        }
    }


    private static long sort(Sort sort, int n) {
        long total;
        Integer[] a = new Integer[n];
        for (int j = 0; j < n; j++) {
            a[j] = random.nextInt(1);
        }
        total = sort.sort(a);
        Assert.assertTrue(sort.isSort(a));
        return total;
    }


}
