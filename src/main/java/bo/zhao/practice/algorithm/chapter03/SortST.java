package bo.zhao.practice.algorithm.chapter03;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/9
 */
public interface SortST<K extends Comparable<K>,V> extends ST<K,V> {

    /**
     * 最小键
     */
    K min();

    /**
     * 最大键
     */
    K max();

    /**
     * 小于等于key的最大键
     */
    K floor(K key);

    /**
     * 大于等于key的最小键
     */
    K ceiling(K key);

    /**
     * 小于key的键的数量
     */
    int rank(K key);

    /**
     * 排名为k的键
     */
    K select(int k);

    /**
     * 删除最小的键
     */
    default void deleteMin() {
        delete(min());
    }

    /**
     * 删除最大的键
     */
    default void deleteMax() {
        delete(max());
    }

    /**
     * [lo...hi]之间键的数量
     */
    default int size(K lo, K hi) {
        if (hi.compareTo(lo) < 0) {
            return 0;
        } else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    /**
     * [lo...hi]之间的所有键，已排序
     */
    Iterable<K> keys(K lo, K hi);

    /**
     * 所有键的集合，已排序
     */
    @Override
    default Iterable<K> keys() {
        if (isEmpty()) {
            return new LinkedQueue<>();
        }
        return keys(min(), max());
    }
}
