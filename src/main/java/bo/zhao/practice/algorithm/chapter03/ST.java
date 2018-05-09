package bo.zhao.practice.algorithm.chapter03;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/8
 */
public interface ST<K, V> {

    /**
     * 将键值对存入表中。
     * 如果value为null，则将key从表中删除。
     */
    void put(K key, V value);

    /**
     * 获取键key对应的值。
     * 如果key不存在则返回null。
     */
    V get(K key);

    /**
     * 在表中删除key和对应的值
     */
    void delete(K key);

    /**
     * 键key在表中是否有对应的值
     */
    boolean contains(K key);

    boolean isEmpty();

    int size();

    /**
     * 所有键的集合，已排序
     */
    Iterable<K> keys();
}
