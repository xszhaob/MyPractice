package bo.zhao.practice.algorithm.chapter02.heapsort;

import bo.zhao.practice.algorithm.chapter02.Sort;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/1
 */
public class HeapSort extends Sort {
    @Override
    public long sort(Comparable[] a) {
        long start = System.currentTimeMillis();
        int n = a.length - 1;
        for (int i = n / 2; i >= 1; i--) {
            sink(a, i, n);
        }

        while (n > 1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }

        return System.currentTimeMillis() - start;
    }


    private void sink(Comparable[] a, int i, int n) {
        while (i * 2 <= n) {
            int j = i * 2;
            if (j < n && less(a[j], a[j + 1])) {
                j++;
            }
            if (!less(a[i], a[j])) {
                break;
            }
            exch(a, i, j);
            i = j;
        }
    }
}
