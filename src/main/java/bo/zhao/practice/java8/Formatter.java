package bo.zhao.practice.java8;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/6
 */
public interface Formatter<T> {

    String accept(T t);
}
