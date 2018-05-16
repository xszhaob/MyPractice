package bo.zhao.practice.algorithm.chapter03;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/16
 */
public class ArrayST<K extends Comparable<K>, V> implements ST<K, V> {

    private K[] keys;

    private V[] values;

    private int count;

    @SuppressWarnings("unchecked")
    public ArrayST(int initialCapacity) {
        keys = (K[]) new Comparable[initialCapacity];
        values = (V[]) new Object[initialCapacity];
    }

    @Override
    public void put(K key, V value) {
        if (value == null) {
            delete(key);
        }
        resize();
        for (int i = 0; i < keys.length; i++) {
            int com = keys[i].compareTo(key);
            if (com == 0) {
                keys[i] = key;
                values[i] = value;
                return;
            }
        }
        keys[count] = key;
        values[count] = value;
        count++;
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < keys.length; i++) {
            int com = keys[i].compareTo(key);
            if (com == 0) {
                return values[i];
            }
        }
        return null;
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            return;
        }
        for (int i = 0; i < keys.length; i++) {
            int com = keys[i].compareTo(key);
            if (com == 0) {
                for (int j = i; j < count - 1; j++) {
                    keys[j] = keys[j + 1];
                    values[j] = values[j + 1];
                }
                keys[count] = null;
                values[count] = null;
                count--;
            }
        }
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<K> keys() {
        return null;
    }


    @SuppressWarnings("unchecked")
    private void resize() {
        if (keys.length == count) {
            K[] newKeys = (K[]) new Comparable[count * 2];
            System.arraycopy(keys, 0, newKeys, 0, count);
            keys = newKeys;
        } else if (keys.length / 4 > count) {
            K[] newKeys = (K[]) new Comparable[count / 2];
            System.arraycopy(keys, 0, newKeys, 0, count);
            keys = newKeys;
        }
    }
}
