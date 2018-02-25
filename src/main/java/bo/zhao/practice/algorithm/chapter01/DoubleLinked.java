package bo.zhao.practice.algorithm.chapter01;

import java.util.Iterator;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/25
 */
public class DoubleLinked<E> implements Iterable<E> {

    private DoubleNode first;
    private DoubleNode last;
    private int n;

    public boolean isEmpty() {
        return n == 0;
    }

    public void addLast(E item) {
        DoubleNode oldLast = last;
        last = new DoubleNode();
        last.item = item;

        if (first == null) {
            first = last;
        } else {
            last.previous = oldLast;
            oldLast.next = last;
        }
        n++;
    }


    public void addFirst(E item) {
        DoubleNode oldFirst = first;
        first = new DoubleNode();
        first.item = item;

        if (oldFirst == null) {
            last = first;
        } else {
            first.next = oldFirst;
            oldFirst.previous = first;
        }
        n++;
    }


    public E pollFirst() {
        if (first == null) {
            return null;
        }
        E item = first.item;
        first = first.next;

        if (first == null) {
            last = null;
        } else {
            first.previous = null;
        }
        n--;
        return item;
    }

    public E pollLast() {
        if (last == null) {
            return null;
        }
        E item = last.item;
        last = last.previous;

        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        n--;
        return item;
    }


    public void addBefore(E item, E newItem) {
        if (first == null) {
            return;
        }

        DoubleNode current = first;
        while (current != null) {
            E tmp = current.item;
            if ((tmp == null && item == null)
                    || (tmp != null && tmp.equals(item))) {
                    DoubleNode node = new DoubleNode();
                    node.item = newItem;
                    node.previous = current.previous;
                    node.next = current;
                    current.previous = node;
                    if (node.previous != null) {
                        node.previous.next = node;
                    } else {
                        first = node;
                    }

                    n++;
                    return;
            }
            current = current.next;
        }
    }


    public void addAfter(E item, E newItem) {
        if (first == null) {
            return;
        }

        DoubleNode current = first;
        while (current != null) {
            E tmp = current.item;
            if ((tmp == null && item == null)
                    || (tmp != null && tmp.equals(item))) {
                DoubleNode node = new DoubleNode();
                node.item = newItem;
                node.next = current.next;
                node.previous = current;
                current.next = node;
                if (node.next != null) {
                    node.next.previous = node;
                } else {
                    last = node;
                }
                n++;
                return;
            }
            current = current.next;
        }
    }


    public E remove(E item) {
        if (first == null) {
            return null;
        }

        DoubleNode current = first;
        int i = 0;
        while (current != null) {
            i++;
            E tmp = current.item;
            if ((tmp == null && item == null)
                    || (tmp != null && tmp.equals(item))) {
                if (i == 1) {
                    current.next.previous = null;
                    first = current.next;
                } else if (i == n){
                    current.previous.next = null;
                    last = current.previous;
                } else {
                    current.next.previous = current.previous;
                    current.previous.next = current.next;
                }
                n--;
                return tmp;
            }
            current = current.next;
        }

        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private class DoubleNode {
        private E item;
        private DoubleNode next;
        private DoubleNode previous;
    }


    public static void main(String[] args) {
        DoubleLinked<String> linked = new DoubleLinked<>();
        linked.addFirst("c");
        linked.addFirst("b");
        linked.addLast("d");
        linked.addLast("e");
        linked.addBefore("b", "a");
        linked.addAfter("e", "f");

        System.out.println(linked.remove("a"));

        while (!linked.isEmpty()) {
            String s = linked.pollLast();
            if (s == null) {
                continue;
            }
            System.out.println(s);
        }
    }
}