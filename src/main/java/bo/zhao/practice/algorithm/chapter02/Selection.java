package bo.zhao.practice.algorithm.chapter02;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/5
 */
public class Selection extends Sort {

    @Override
    public long sort(Comparable[] a) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            if (min != i) {
                exch(a, i, min);
            }
        }
        return System.currentTimeMillis() - start;
    }
}
