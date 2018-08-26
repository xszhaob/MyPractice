package bo.zhao.practice.algorithm.chapter03._3_4;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;
import bo.zhao.practice.algorithm.chapter03.ST;

/**
 * 基于线性探测法的散列表。
 * 用大小为M的数组保存N个键值对，其中M>N。
 * 我们需要依靠数组中的空位解决碰撞冲突。
 * 基于这种策略的所有方法被统称为开放地址散列表。
 * 开放地址散列表中最简单的方法叫做线性探测法：
 * 当碰撞发生时（当一个键的散列值已经被另一个不同的键占用），
 * 我们直接检查散列表中的下一个位置（将索引加1）。
 * 这样的线性探测可能会产生三种结果：
 * 1）命中，该位置的键和被查找的键相同；
 * 2）未命中，键为空（该位置没有键）；
 * 3）继续查找，该位置的键和被查找的键不同。
 *
 * @author Bo.Zhao
 * @since 18/8/26
 */
public class LinearProbingHashST<K, V> implements ST<K, V> {

    private int n;
    private int m = 16;

    private K[] keys;
    private V[] vals;

    @SuppressWarnings("unchecked")
    public LinearProbingHashST(int capacity) {
        keys = (K[]) new Object[capacity];
        vals = (V[]) new Object[capacity];
        n = 0;
        m = capacity;
    }

    public LinearProbingHashST() {
        this(16);
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        if (value == null) {
            delete(key);
            return;
        }
        // 动态扩容
        if (n >= m / 2) {
            resize(m * 2);
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = value;
                return;
            }
        }
        keys[i] = key;
        vals[i] = value;
        n++;
    }

    @Override
    public V get(K key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    @Override
    public void delete(K key) {
        if (!contains(key)) {
            return;
        }
        int i = hash(key);
        while (!keys[i].equals(key)) {
            i = (i + 1) % m;
        }
        keys[i] = null;
        vals[i] = null;

        i = (i + 1) % m;
        while (keys[i] != null) {
            K k = keys[i];
            V v = vals[i];
            keys[i] = null;
            vals[i] = null;
            // 每次插入时会更新n的数据，加1，所以要事先减去1
            n--;
            put(k, v);
            i = (i + 1) % m;
        }
        n--;
        if (n > 0 && n <= m / 8) {
            resize(m / 2);
        }
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterable<K> keys() {
        LinkedQueue<K> queue = new LinkedQueue<>();
        if (n == 0) {
            return queue;
        }
        for (K key : keys) {
            if (key == null) {
                continue;
            }
            queue.enqueue(key);
        }
        return queue;
    }

    private void resize(int capacity) {
        LinearProbingHashST<K, V> t = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        m = t.m;
    }

    private int hash(K key) {
        return key.hashCode() & 0x7fffffff % m;
    }
}
