package bo.zhao.practice.algorithm.chapter02;

import bo.zhao.practice.algorithm.Stopwatch;

import java.util.Arrays;
import java.util.Random;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/11
 */
public class MergeBU extends BaseMergeSort {

    @Override
    public long sort(Comparable[] a) {
        Stopwatch stopwatch = new Stopwatch();
        int n = a.length;
        AUX = new Comparable[n];

        for (int sz = 1; sz < n; sz = sz + sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }

        return stopwatch.elapsedTime();
    }


    public static void main(String[] args) {
        int n = 32;
        Comparable[] mergeBuA = new Comparable[n];
        Comparable[] mergeA = new Comparable[n];
        for (int i = 0; i < n; i++) {
            mergeBuA[i] = n - i;
            mergeA[i] = n - i;
        }
        MergeBU mergeBU = new MergeBU();
        mergeBU.sort(mergeBuA);

        System.out.println("*************************************");

        Merge merge = new Merge();
        merge.sort(mergeA);
    }
}
