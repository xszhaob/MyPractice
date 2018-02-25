package bo.zhao.practice.algorithm.chapter01.practice;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/25
 */
public class Ex1_3_29<E> {
    private Node<E> last;
    private int n;

    private void enqueue(E e) {
        Node<E> oldLast = last;
        last = new Node<>();
        last.item = e;

        if (oldLast == null) {
            last.next = last;
        } else {
            last.next = oldLast.next;
            oldLast.next = last;
        }
        n++;
    }

    public E dequeue() {
        if (n == 0) throw new RuntimeException("Queue underflow");
        E item = last.next.item;
        if (last.next == last) {
            last = null;
        } else {
            last.next = last.next.next;
        }
        n--;
        return item;
    }


    private class Node<T> {
        private T item;
        private Node<T> next;
    }


    public static void main(String[] args) {
        Ex1_3_29<String> ex = new Ex1_3_29<>();
        ex.enqueue("a");
        ex.enqueue("b");
        ex.enqueue("c");
        ex.enqueue("d");

        System.out.println(ex.last.next.next.item);
    }
}