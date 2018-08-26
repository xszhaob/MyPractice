package bo.zhao.practice.algorithm.chapter03._3_4;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;
import bo.zhao.practice.algorithm.chapter03.ST;

/**
 * @author Bo.Zhao
 * @since 18/8/26
 */
public class SeparateChainingHashST<K, V> implements ST<K, V> {

    private int n;
    private int m;
    private Node<K, V>[] table;

    public SeparateChainingHashST() {
        this(997);
    }

    @SuppressWarnings("unchecked")
    public SeparateChainingHashST(int m) {
        this.m = m;
        table = (Node<K, V>[]) new Node[m];
    }

    @Override
    public void put(K key, V value) {
        int tableIndex = hash(key);
        Node<K, V> node = table[tableIndex];
        if (node == null) {
            table[tableIndex] = new Node<>(key, value);
            n++;
        } else {
            Node<K, V> tmp = node;
            Node<K, V> prev = null;
            while (tmp != null) {
                if (tmp.key.equals(key)) {
                    tmp.value = value;
                    return;
                }
                prev = tmp;
                tmp = tmp.next;
            }
            prev.next = new Node<>(key, value);
            n++;
        }
    }

    @Override
    public V get(K key) {
        int tableIndex = hash(key);
        Node<K, V> node = table[tableIndex];
        if (node == null) {
            return null;
        }
        Node<K, V> tmp = node;
        while (tmp != null) {
            if (tmp.key.equals(key)) {
                return tmp.value;
            }
            tmp = tmp.next;
        }
        return null;
    }

    @Override
    public void delete(K key) {
        int tableIndex = hash(key);
        Node<K, V> node = table[tableIndex];
        if (node == null) {
            return;
        }
        Node<K, V> tmp = node;
        Node<K, V> prev = null;
        while (tmp != null) {
            if (tmp.key.equals(key)) {
                if (prev == null) {
                    table[tableIndex] = tmp.next;
                } else {
                    prev.next = tmp.next;
                }
                n--;
                return;
            }
            prev = tmp;
            tmp = tmp.next;
        }
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterable<K> keys() {
        LinkedQueue<K> queue = new LinkedQueue<>();
        if (isEmpty()) {
            return queue;
        }
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            if (node == null) {
                continue;
            }
            Node<K, V> tmp = node;
            while (tmp != null) {
                queue.enqueue(tmp.key);
                tmp = tmp.next;
            }
        }
        return queue;
    }


    private int hash(K key) {
        return key.hashCode() & 0x7fffffff % m;
    }

    private class Node<IK, IV> {
        private Node<IK, IV> next;
        private IK key;
        private IV value;

        public Node(IK key, IV value) {
            this.key = key;
            this.value = value;
        }
    }
}
