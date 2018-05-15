package bo.zhao.practice.algorithm.chapter03;

import java.util.Iterator;

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
        int i = rank(key);
        if (i < count && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        // 动态扩容
        resize();
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
        return keys[rank(key)];
    }

    @Override
    public K ceiling(K key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        // 大于最大值
        if (i == count) {
            return null;
        }
        // 小于最小值
        if (i == 0) {
            return keys[0];
        }
        // 正常情况
        return keys[i + 1];
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
        if (keys[i].compareTo(key) == 0) {
            System.arraycopy(keys, i + 1, keys, i, count - 1 - i);
        }
        keys[count - 1] = null;
        count--;
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
        int i = rank(key);
        return keys[i].compareTo(key) == 0;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public K select(int k) {
        return keys[k];
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        if (lo.compareTo(hi) >= 0) {
            return null;
        }
        int from = rank(lo);
        int to = rank(hi);

        return new BinarySearchSTKeyIterable(from, to);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        if (keys.length == count) {
            K[] newKeys = (K[]) new Comparable[count * 2];
            System.arraycopy(keys, 0, newKeys, 0, count);
            keys = newKeys;
            V[] newValues = (V[]) new Object[count * 2];
            System.arraycopy(values, 0, newValues, 0, count);
            values = newValues;
        }
    }


    private class BinarySearchSTKeyIterable implements Iterable<K> {
        private final int lo;
        private final int hi;

        BinarySearchSTKeyIterable(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        public Iterator<K> iterator() {
            return new BinarySearchSTKeyIterator(lo, hi);
        }
    }


    private class BinarySearchSTKeyIterator implements Iterator<K> {

        private final int hi;
        private int currentIndex;

        BinarySearchSTKeyIterator(int lo, int hi) {
            this.hi = hi;
            this.currentIndex = lo;
        }

        @Override
        public boolean hasNext() {
            return currentIndex <= hi;
        }

        @Override
        public K next() {
            return keys[currentIndex++];
        }
    }
}
