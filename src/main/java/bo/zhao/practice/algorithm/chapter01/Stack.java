package bo.zhao.practice.algorithm.chapter01;

/**
 * Created by zhaobo on 2018/2/22.
 */
public interface Stack<E> extends MyIterable<E> {

    boolean isEmpty();

    int size();

    void push(E e);

    E pop();

    E peek();
}
