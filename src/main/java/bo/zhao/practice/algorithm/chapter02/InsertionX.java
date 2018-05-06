package bo.zhao.practice.algorithm.chapter02;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/8
 */
public class InsertionX extends Sort {

    @Override
    public long sort(Comparable[] a) {
        long start = System.currentTimeMillis();
        int minIndex = 0;
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[minIndex])) {
                minIndex = i;
            }
        }
        if (minIndex != 0) {
            exch(a, 0, minIndex);
        }

        for (int i = 2; i < a.length; i++) {
            Comparable v = a[i];
            int j = i;
            while (less(v, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }
        return System.currentTimeMillis() - start;
    }
}
