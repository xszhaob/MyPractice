package bo.zhao.practice.algorithm.chapter01.practice;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/25
 */
public class Ex1_3_19<E> {

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

    public E popLast() {
        if (n == 0) {
            return null;
        }

        if (n == 1) {
            E e = first.item;
            first = null;
            last = null;
            return e;
        }

        int i = n;
        Node<E> tmp = first;
        while (--i > 1) {
            tmp = tmp.next;
        }

        E e = last.item;
        tmp.next = null;
        last = tmp;
        n--;
        return e;
    }



    private class Node<T> {
        private T item;
        private Node<T> next;
    }


    public static void main(String[] args) {
        Ex1_3_19<String> ex = new Ex1_3_19<>();
        ex.push("a");
        ex.push("b");
        ex.push("c");
        ex.push("d");

        System.out.println(ex.popLast());
        System.out.println(ex.popLast());
        System.out.println(ex.popLast());
        System.out.println(ex.popLast());
    }
}
