package bo.zhao.practice.algorithm.chapter03;

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

    @Override
    public K min() {
        return null;
    }

    @Override
    public void put(K key, V value) {
        int i = rank(key);
        if (i < count && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        // 动态扩容
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
        return null;
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
    public K floor() {
        return null;
    }

    @Override
    public K ceiling(K key) {
        return null;
    }

    @Override
    public void delete(K key) {

    }

    @Override
    public int rank(K key) {
        return 0;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public K select(int k) {
        return null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        if (keys.length == count) {
            K[] newKeys = (K[]) new Comparable[count * 2];
            System.arraycopy(keys, 0, newKeys, 0, count);
            keys = newKeys;
        }
    }
}
