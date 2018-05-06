package bo.zhao.practice.algorithm.chapter02;

import bo.zhao.practice.algorithm.Stopwatch;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/11
 */
abstract class BaseMergeSort extends Sort {

    protected static Comparable[] AUX;
    public AtomicInteger count = new AtomicInteger(0);

    long merge(Comparable[] a, int lo, int mid, int hi) {
        Stopwatch stopwatch = new Stopwatch();

//        print(a, lo, mid, hi);

        int i = lo;
        int j = mid + 1;
        int n = hi - lo + 1;

        System.arraycopy(a, lo, AUX, lo, n);
        count.addAndGet(n * 2);

        for (int m = lo; m <= hi; m++) {
            if (i > mid) {
                a[m] = AUX[j++];
                count.addAndGet(2);
            } else if (j > hi) {
                a[m] = AUX[i++];
                count.addAndGet(2);
            } else if (less(AUX[i], AUX[j])) {
                a[m] = AUX[i++];
                count.addAndGet(4);
            } else {
                a[m] = AUX[j++];
                count.addAndGet(4);
            }
        }

        return stopwatch.elapsedTime();
    }


    private void print(Comparable[] a, int lo, int mid, int hi) {
        for (int i = lo; i <= mid; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.print("&&");
        for (int j = mid + 1; j <= hi; j++) {
            System.out.print(a[j] + ",");
        }
        System.out.println("");
    }
}
