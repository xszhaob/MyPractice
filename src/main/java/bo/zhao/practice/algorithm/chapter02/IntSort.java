package bo.zhao.practice.algorithm.chapter02;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/8
 */
public class IntSort {
    public long sort(int[] a) {
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

    @SuppressWarnings("unchecked")
    private boolean less(int v, int w) {
        return v < w;
    }

    protected void exch(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    protected void show(int[] a) {
        for (int c : a) {
            System.out.print(c + " ");
        }
    }

    protected boolean isSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
