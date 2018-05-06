package bo.zhao.practice.algorithm.chapter02;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/7
 */
public class Shell extends Sort {
    private static final int SHELL_H = 3;

    @Override
    public long sort(Comparable[] a) {
        long start = System.currentTimeMillis();
        int h = 1;
        int n = a.length;
        while (h < n / SHELL_H) {
            h = h * SHELL_H + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(a[j], a[j - h])) {
                        exch(a, j, j - h);
                    } else {
                        break;
                    }
                }
            }
            h = h / SHELL_H;
        }

        return System.currentTimeMillis() - start;
    }
}
