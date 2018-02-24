package bo.zhao.practice.algorithm.chapter01;

import org.omg.DynamicAny.NameDynAnyPair;

import java.util.Iterator;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/24
 */
public class LinkedQueue<E> implements Queue<E>, Iterable<E> {

    private Node first;
    private Node last;
    private int n;

    @Override
    public void enqueue(E e) {
        Node oldLast = last;
        last = new Node();
        last.item = e;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    @Override
    public E dequeue() {
        E item = first.item;
        first = first.next;
        // 只有一个元素
        if (isEmpty()) {
            last = null;
        }
        n--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedQueueIterator();
    }

    private class Node {
        private E item;

        private Node next;
    }


    private class LinkedQueueIterator implements Iterator<E> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            Node tmp = current;
            current = current.next;
            return tmp.item;
        }

        @Override
        public void remove() {

        }
    }


    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("hello");
        queue.enqueue("world");
        queue.enqueue("bo");
        queue.enqueue("zhao");

        for (String s : queue) {
            System.out.println(s);
        }


    }
}
