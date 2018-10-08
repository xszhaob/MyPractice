package bo.zhao.practice.concurrency;

import java.util.*;

public class SynchronizedLinkedList<E> extends LinkedList<E> {


    public SynchronizedLinkedList() {
        super();
    }

    public SynchronizedLinkedList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public synchronized E getFirst() {
        return super.getFirst();
    }

    @Override
    public synchronized E getLast() {
        return super.getLast();
    }

    @Override
    public synchronized E removeFirst() {
        return super.removeFirst();
    }

    @Override
    public synchronized E removeLast() {
        return super.removeLast();
    }

    @Override
    public synchronized void addFirst(E e) {
        super.addFirst(e);
    }

    @Override
    public synchronized void addLast(E e) {
        super.addLast(e);
    }

    @Override
    public synchronized boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public synchronized int size() {
        return super.size();
    }

    @Override
    public synchronized boolean add(E e) {
        return super.add(e);
    }

    @Override
    public synchronized boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public synchronized boolean addAll(Collection<? extends E> c) {
        return super.addAll(c);
    }

    @Override
    public synchronized boolean addAll(int index, Collection<? extends E> c) {
        return super.addAll(index, c);
    }

    @Override
    public synchronized void clear() {
        super.clear();
    }

    @Override
    public synchronized E get(int index) {
        return super.get(index);
    }

    @Override
    public synchronized E set(int index, E element) {
        return super.set(index, element);
    }

    @Override
    public synchronized void add(int index, E element) {
        super.add(index, element);
    }

    @Override
    public synchronized E remove(int index) {
        return super.remove(index);
    }

    @Override
    public synchronized int indexOf(Object o) {
        return super.indexOf(o);
    }

    @Override
    public synchronized int lastIndexOf(Object o) {
        return super.lastIndexOf(o);
    }

    @Override
    public synchronized E peek() {
        return super.peek();
    }

    @Override
    public synchronized E element() {
        return super.element();
    }

    @Override
    public synchronized E poll() {
        return super.poll();
    }

    @Override
    public synchronized E remove() {
        return super.remove();
    }

    @Override
    public synchronized boolean offer(E e) {
        return super.offer(e);
    }

    @Override
    public synchronized boolean offerFirst(E e) {
        return super.offerFirst(e);
    }

    @Override
    public synchronized boolean offerLast(E e) {
        return super.offerLast(e);
    }

    @Override
    public synchronized E peekFirst() {
        return super.peekFirst();
    }

    @Override
    public synchronized E peekLast() {
        return super.peekLast();
    }

    @Override
    public synchronized E pollFirst() {
        return super.pollFirst();
    }

    @Override
    public synchronized E pollLast() {
        return super.pollLast();
    }

    @Override
    public synchronized void push(E e) {
        super.push(e);
    }

    @Override
    public synchronized E pop() {
        return super.pop();
    }

    @Override
    public synchronized  boolean removeFirstOccurrence(Object o) {
        return super.removeFirstOccurrence(o);
    }

    @Override
    public synchronized boolean removeLastOccurrence(Object o) {
        return super.removeLastOccurrence(o);
    }

    @Override
    public synchronized ListIterator<E> listIterator(int index) {
        return super.listIterator(index);
    }

    @Override
    public synchronized Iterator<E> descendingIterator() {
        return super.descendingIterator();
    }

    @Override
    public synchronized Object clone() {
        return super.clone();
    }

    @Override
    public synchronized Object[] toArray() {
        return super.toArray();
    }

    @Override
    public synchronized <T> T[] toArray(T[] a) {
        return super.toArray(a);
    }

    @Override
    public synchronized Spliterator<E> spliterator() {
        return super.spliterator();
    }
}
