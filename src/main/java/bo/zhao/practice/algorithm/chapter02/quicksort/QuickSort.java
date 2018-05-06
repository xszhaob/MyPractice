package bo.zhao.practice.algorithm.chapter02.quicksort;

import bo.zhao.practice.algorithm.chapter02.Sort;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/19
 */
public class QuickSort extends Sort {

    @Override
    public long sort(Comparable[] a) {
        long start = System.currentTimeMillis();

        sort(a, 0, a.length - 1);
        return System.currentTimeMillis() - start;
    }


    private void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }


    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
}
