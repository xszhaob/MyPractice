package bo.zhao.practice.algorithm.chapter01;

import java.util.Iterator;

/**
 * @author Bo.Zhao
 * @since 18/9/14
 */
public interface MyIterable<E> extends Iterable<E> {

    default String string() {
        Iterator<E> iterator = iterator();
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        while (iterator.hasNext()) {
            if (i != 0) {
                sb.append(", ").append(iterator.next().toString());
            } else {
                sb.append(iterator.next().toString());
            }
            i++;
        }
        sb.append("]");
        return sb.toString();
    }
}
