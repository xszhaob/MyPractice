package bo.zhao.practice.algorithm.chapter03;

/**
 * Created by zhaobo on 2018/5/9.
 */
public class Tuple<K, V> {
    private final K key;

    private final V value;

    public Tuple(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
