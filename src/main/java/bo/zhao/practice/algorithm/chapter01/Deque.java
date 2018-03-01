package bo.zhao.practice.algorithm.chapter01;

/**
 * Created by zhaobo on 2018/2/28.
 */
public interface Deque<E> {

    int size();

    boolean isEmpty();

    void pushLeft(E item);

    void pushRight(E item);

    E popLeft();

    E popRight();
}
