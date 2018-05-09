package bo.zhao.practice.algorithm.chapter03;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/9
 */
public class Node<K, V> {
    private Node<K, V> next;

    private K key;

    private V value;

    public Node(Node<K, V> next, K key, V value) {
        this.next = next;
        this.key = key;
        this.value = value;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
