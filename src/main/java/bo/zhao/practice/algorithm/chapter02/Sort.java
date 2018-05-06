package bo.zhao.practice.algorithm.chapter02;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/5
 */
public abstract class Sort {

    public int comCount = 0;

    public abstract long sort(Comparable[] a);

    @SuppressWarnings("unchecked")
    protected boolean less(Comparable v, Comparable w) {
        comCount++;
        return v.compareTo(w) < 0;
    }

    protected boolean elt(Comparable v, Comparable w) {
        comCount++;
        return v.compareTo(w) <= 0;
    }

    protected void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    protected void show(Comparable[] a) {
        for (Comparable c : a) {
            System.out.print(c + " ");
        }
    }

    protected boolean isSort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

}
