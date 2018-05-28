package bo.zhao.practice.algorithm.chapter03;

import java.util.Iterator;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/9
 */
public class SequentialSearchST<K extends Comparable<K>, V> implements ST<K, V> {
    private Node<K, V> first;

    private int count;

    @Override
    public void put(K key, V value) {
        Node<K, V> x = first;
        while (x != null) {
            if (key.equals(x.getKey())) {
                x.setValue(value);
                return;
            }
            x = x.getNext();
        }
        count++;
        first = new Node<>(first, key, value);
    }

    @Override
    public V get(K key) {
        Node<K, V> x = first;
        while (x != null) {
            if (key.equals(x.getKey())) {
                return x.getValue();
            }
            x = x.getNext();
        }
        return null;
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        first = delete(first, key);
    }

    /**
     * 递归的方式删除节点信息
     */
    private Node<K,V> delete(Node<K,V> x, K key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.getKey())) {
            count--;
            return x.getNext();
        }
        x.setNext(delete(x.getNext(), key));
        return x;
    }

    @Override
    public boolean contains(K key) {
        Node<K, V> x = first;
        while (x != null) {
            if (key.equals(x.getKey())) {
                return true;
            }
            x = x.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterable<K> keys() {
        return new SequentialSearchIt();
    }

    private class SequentialSearchIt implements Iterable<K> {
        @Override
        public Iterator<K> iterator() {
            return new SequentialSearchSTIterator();
        }
    }

    private class SequentialSearchSTIterator implements Iterator<K> {

        private Node<K, V> node = first;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public K next() {
            Node<K, V> tmp = node;
            node = node.getNext();
            return tmp.getKey();
        }
    }
}
