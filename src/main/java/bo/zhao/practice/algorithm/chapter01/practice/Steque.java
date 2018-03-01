package bo.zhao.practice.algorithm.chapter01.practice;

import java.util.Iterator;

/**
 * Created by zhaobo on 2018/2/28.
 */
public class Steque<E> implements Iterable<E> {

    private int count = 0;
    private Node first;
    private Node last;

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void push(E item) {
        Node tmp = first;
        first = new Node(item);

        if (isEmpty()) {
            last = first;
        } else {
            first.next = tmp;
        }
        count++;
    }


    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E item = first.item;
        if (first.next == null) {
            first = null;
            last = null;
        } else {
            first = first.next;
        }
        count--;
        return item;
    }


    public void enqueue(E item) {
        Node oldLast = last;
        last = new Node(item);

        if (oldLast == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
        count++;
    }

    @Override
    public Iterator<E> iterator() {
        return new StequeIterator();
    }


    private class Node {
        private E item;
        private Node next;

        public Node(E item) {
            this.item = item;
        }
    }

    private class StequeIterator implements Iterator<E> {
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E e : this) {
            sb.append(e).append(",");
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
}
