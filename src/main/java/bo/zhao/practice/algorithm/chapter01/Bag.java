package bo.zhao.practice.algorithm.chapter01;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/3/1
 */
public interface Bag<E> extends MyIterable<E> {
    boolean isEmpty();

    int size();

    void add(E item);
}
