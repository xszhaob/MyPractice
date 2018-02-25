package bo.zhao.practice.algorithm.chapter01.practice;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/25
 */
public class Ex1_3_24<E> {


    private Node<E> first;
    private Node<E> last;

    private int n;

    public void add(E item) {
        Node<E> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (first == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }


    public void removerAfter(E item) {
        if (first == null) {
            return;
        }
        Node<E> current = first;
        int i = 0;
        while (current != null) {
            i++;
            if ((current.item == null && item == null)
                    || (current.item != null && current.item.equals(item))) {
                current.next = null;
                last = current;
                n = i;
                return;
            }
            current = current.next;
        }
    }

    public int size() {
        return n;
    }


    private class Node<T> {
        private T item;
        private Node<T> next;
    }


    public static void main(String[] args) {
        Ex1_3_24<String> ex = new Ex1_3_24<>();
        ex.add("a");
        ex.add("b");
        ex.add("c");
        ex.add("d");


        ex.removerAfter("c");
        System.out.println(ex.size());
    }
}
