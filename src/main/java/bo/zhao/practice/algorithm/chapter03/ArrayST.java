package bo.zhao.practice.algorithm.chapter03;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;

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
        for (int i = 0; i < count; i++) {
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
        for (int i = 0; i < count; i++) {
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
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterable<K> keys() {
        LinkedQueue<K> queue = new LinkedQueue<>();
        for (int i = 0; i < count; i++) {
            queue.enqueue(keys[i]);
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
