package bo.zhao.practice.algorithm.chapter02;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/5
 */
public class InsertionOneChange extends Sort {
    @Override
    public long sort(Comparable[] a) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < a.length - 1; i++) {
            Comparable v = a[i + 1];
            int j = i + 1;
            for (; j > 0; j--) {
                if (less(v, a[j - 1])) {
                    a[j] = a[j - 1];
                } else {
                    break;
                }
            }
            a[j] = v;
        }
        return System.currentTimeMillis() - start;
    }
}
