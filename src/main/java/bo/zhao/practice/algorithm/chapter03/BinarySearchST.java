package bo.zhao.practice.algorithm.chapter03;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/10
 */
public class BinarySearchST<K extends Comparable<K>, V> implements SortST<K, V> {

    private K[] keys;

    private V[] values;

    private int count;

    @SuppressWarnings("unchecked")
    public BinarySearchST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        values = (V[]) new Comparable[capacity];
    }

    /**
     * 常数级别的操作
     */
    @Override
    public K min() {
        return keys[0];
    }

    @Override
    public void put(K key, V value) {
        // 如果value=null，认为是删除数据
        if (value == null) {
            delete(key);
        }
        // 已经包含了该key，替换
        int i = rank(key);
        if (i < count && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        // 动态扩容
        resize();
        // key后面的数据往后移
        for (int j = count; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        count++;
    }

    @Override
    public K max() {
        return keys[count - 1];
    }

    @Override
    public V get(K key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < count && keys[i].compareTo(key) == 0) {
            return values[i];
        }
        return null;
    }

    @Override
    public K floor(K key) {
        // 找到小于k的符号的数量
        int i = rank(key);
        /*
        如果i小于count 并且keys[i].compareTo(key) == 0，则返回keys[i]。
        这里包含刚好key就是最小符号的情况
         */
        if (i < count && keys[i].compareTo(key) == 0) {
            return keys[i];
        }
        // 如果key小于最小符号的情况
        if (i == 0) {
            return null;
        }
        // 包括了key大于最小符号的所有场景
        return keys[i - 1];
    }

    @Override
    public K ceiling(K key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        // 大于最大符号
        if (i == count) {
            return null;
        }
        // 小于最大符号以外的所有场景
        return keys[i];
    }

    @Override
    public void delete(K key) {
        if (count == 0) {
            return;
        }
        int i = rank(key);
        if (i == count) {
            return;
        }
        int com = keys[i].compareTo(key);
        if (com != 0) {
            return;
        }
        // 两个复制操作
        System.arraycopy(keys, i + 1, keys, i, count - 1 - i);
        System.arraycopy(values, i + 1, values, i, count - 1 - i);
        keys[count - 1] = null;
        values[count - 1] = null;
        count--;
        resize();
    }

    @Override
    public int rank(K key) {
        int lo = 0;
        int hi = count - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int com = keys[mid].compareTo(key);
            if (com > 0) {
                hi = mid - 1;
            } else if (com < 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public K select(int k) {
        if (k < 0 || k >= size()) {
            throw new RuntimeException("called select() with invalid argument:" + k);
        }
        return keys[k];
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        LinkedQueue<K> queue = new LinkedQueue<>();
        if (lo.compareTo(hi) > 0) {
            return queue;
        }

        int from = rank(lo);
        int to = rank(hi);

        for (int i = from; i < to; i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) {
            queue.enqueue(keys[to]);
        }
        return queue;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        if (keys.length == count) {
            K[] newKeys = (K[]) new Comparable[count * 2];
            V[] newValues = (V[]) new Object[count * 2];
            System.arraycopy(keys, 0, newKeys, 0, count);
            System.arraycopy(values, 0, newValues, 0, count);
            keys = newKeys;
            values = newValues;
        } else if (keys.length / 4 > count) {
            K[] newKeys = (K[]) new Comparable[keys.length / 2];
            V[] newValues = (V[]) new Object[values.length / 2];
            System.arraycopy(keys, 0, newKeys, 0, count);
            System.arraycopy(values, 0, newValues, 0, count);
            keys = newKeys;
            values = newValues;
        }
    }
}
