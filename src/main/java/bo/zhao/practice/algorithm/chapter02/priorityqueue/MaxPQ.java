package bo.zhao.practice.algorithm.chapter02.priorityqueue;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/30
 */
public class MaxPQ<T extends Comparable<T>> {
    private int count = 0;
    private T[] a;

    public T[] getA() {
        return a;
    }

    @SuppressWarnings("unchecked")
    public MaxPQ(int count) {
        a = (T[]) new Comparable[count];
    }

    /**
     * 向优先级队列中插入一个元素
     */
    public void insert(T key) {
        a[++count] = key;
        swim(count);
    }

    /**
     * 返回最大的元素
     */
    public T max() {
        return a[1];
    }

    /**
     * 删除并返回最大的元素
     */
    public T delMax() {
        T max = a[1];
        exch(1, count--);
        a[count + 1] = null;
        sink(1);
        return max;
    }

    /**
     * 返回队列是否为空
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 返回优先级队列中的元素个数
     */
    int size() {
        return count;
    }


    @SuppressWarnings("unchecked")
    protected boolean less(int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    protected void exch(int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * 由下至上的堆排序（上浮）
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 由上至下的堆排序（下沉）
     */
    private void sink(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            // 比较得出两个节点上相对较大的值。如果j=count说明只有一个节点
            if (j < count && less(j, j + 1)) {
                j++;
            }
            // 如果节点上大的值没有k大，说明已经完成下沉，退出循环
            if (!less(k, j)) {
                break;
            }
            // 交换k和较大的节点j
            exch(k, j);
            // 继续下沉
            k = j;
        }
    }
}
