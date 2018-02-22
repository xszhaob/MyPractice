package bo.zhao.practice.algorithm.chapter01;

import java.util.Iterator;

/**
 * Created by zhaobo on 2018/2/22.
 */
public class LinkedStack<E> implements Iterable<E> {

    private Node first;
    private int n;

    private boolean isEmpty() {
        return n == 0;
    }

    private int size() {
        return n;
    }

    private void push(E e) {
        Node oldFirst = first;
        first = new Node();
        first.item = e;
        first.next = oldFirst;
        n++;
    }

    public E pop() {
        E e = first.item;
        first = first.next;
        n--;
        return e;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedStackIterator();
    }

    private class Node {
        private E item;

        private Node next;
    }


    private class LinkedStackIterator implements Iterator<E> {
        private Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            E item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }


    public static void main(String[] args) {
        LinkedStack<String> linkedStack = new LinkedStack<String>();
        linkedStack.push("hello");
        linkedStack.push("world");
        linkedStack.push("bo");
        linkedStack.push("zhao");
        for (String s : linkedStack) {
            System.out.println(s);
        }

        System.out.println(linkedStack.size());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.size());
    }
}
