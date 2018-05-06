package bo.zhao.practice.algorithm.chapter02;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/5
 */
public class Insertion extends Sort {
    @Override
    public long sort(Comparable[] a) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j - 1, j);
                } else {
                    break;
                }
            }
        }
        return System.currentTimeMillis() - start;
    }
}
