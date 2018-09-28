package bo.zhao.practice.algorithm.chapter01;

import java.util.Iterator;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/24
 */
public class LinkedBag<E> implements Bag<E> {

    private Node first;
    private int n;

    @Override
    public void add(E e) {
        Node tmp = first;
        first = new Node();
        first.item = e;
        first.next = tmp;
        n++;
    }

    @Override
    public boolean isEmpty() {
        return n > 0;
    }

    @Override
    public int size() {
        return n;
    }


    @Override
    public Iterator<E> iterator() {
        return new LinkedBagIterator();
    }

    public class LinkedBagIterator implements Iterator<E> {
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

    private class Node {
        private E item;

        private Node next;
    }

    @Override
    public String toString() {
        return string();
    }


    public static void main(String[] args) {
        LinkedBag<String> bag = new LinkedBag<>();
        bag.add("hello");
        bag.add("world");
        bag.add("bo");
        bag.add("zhao");

        System.out.println(bag.size());

        for (String s : bag) {
            System.out.println(s);
        }
    }
}
