package bo.zhao.practice.algorithm.chapter01.practice;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/25
 */
public class Ex1_3_20<E> {

    private Node<E> first;
    private Node<E> last;
    private int n;

    private void push(E item) {
        Node<E> tmp = last;
        last = new Node<>();
        last.item = item;
        if (first == null) {
            first = last;
        } else {
            last.next = null;
            tmp.next = last;
        }
        n++;
    }

    public E delete(int k) {
        if (k > n) {
            throw new UnsupportedOperationException();
        }

        if (k== 1) {
            E item = first.item;
            first = null;
            last = null;
            return item;
        }

        int i = 1;
        Node<E> tmp = first;
        while (++i < k) {
            tmp = tmp.next;
        }
        E item = tmp.next.item;
        tmp.next = tmp.next.next;
        if (n == k) {
            last = tmp;
        }

        n--;

        return item;
    }



    private class Node<T> {
        private T item;
        private Node<T> next;
    }


    public static void main(String[] args) {
        Ex1_3_20<String> ex = new Ex1_3_20<>();
        ex.push("a");
        ex.push("b");
        ex.push("c");
        ex.push("d");

        System.out.println(ex.delete(4));
        System.out.println(ex.delete(3));
        System.out.println(ex.delete(2));
        System.out.println(ex.delete(1));
    }
}
