package bo.zhao.practice.algorithm.chapter02;

import bo.zhao.practice.algorithm.chapter02.quicksort.Quick3way;
import bo.zhao.practice.algorithm.chapter02.quicksort.QuickSort;
import org.junit.Assert;

import java.util.Random;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/6
 */
public class SortCompare {
    private static Random random = new Random();

    public static void main(String[] args) {
        int t = 10;
        int n = 1000000;

        long selectionCost = timeRandomInput(new Quick3way(), n, t);
//        long insertionCost = timeRandomInput(new QuickSort(), n, t);
//        long insertionXCost = timeRandomInput(new InsertionX(), n, t);
//        long shellCost = timeRandomInput(new Shell(), n, t);

        System.out.println("selectionCost " + selectionCost + " ms");
//        System.out.println("insertionCost " + insertionCost + " ms");
//        System.out.println("insertionXCost " + insertionXCost + " ms");
//        System.out.println("shellCost " + shellCost + " ms");
//        System.out.println("Selection/Insertion = " + (((double)selectionCost) / insertionCost));
    }

    public static long timeRandomInput(Sort sort, int n, int t) {
        long total = 0;

        Integer[] a = new Integer[n];

        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++) {
                a[j] = random.nextInt(2);
            }
            long costTime = sort.sort(a);
            total += costTime;
            System.out.println("cost time " + costTime);
            Assert.assertTrue(sort.isSort(a));
        }
        return total;
    }

    public static long timeRandomInput(IntSort sort, int n, int t) {
        long total = 0;

        int[] a = new int[n];

        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++) {
                a[j] = random.nextInt(n);
            }
            total = sort.sort(a);
            Assert.assertTrue(sort.isSort(a));
        }
        return total;
    }
}
