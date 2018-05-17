package bo.zhao.practice.algorithm.chapter03;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/17
 */
public class OrderSequentialSearchST<K extends Comparable<K>, V> implements SortST<K, V> {

    private Node<K, V> first;

    private Node<K, V> last;

    private int count;


    @Override
    public K min() {
        if (count == 0) {
            return null;
        }
        return first.getKey();
    }

    @Override
    public void put(K key, V value) {
        if (first == null) {
            first = new Node<>(key, value);
            last = first;
        }
        Node<K,V> tmp = first;
        Node<K,V> previousNode = null;
        while (tmp != null) {
            int com = tmp.getKey().compareTo(key);
            if(com == 0) {
                tmp.setKey(key);
                tmp.setValue(value);
            } else if (com < 0) {
                if (previousNode == null) {
                    previousNode = new Node<>(key, value);
                    previousNode.setNext(first);
                    first = previousNode;
                } else {
                    Node<K, V> node = new Node<>(key, value);
                    node.setNext(tmp);
                    previousNode.setNext(node);
                    count++;
                    break;
                }
            }
            previousNode = tmp;
            tmp = tmp.getNext();
        }
        Node<K, V> node = new Node<>(key, value);
        last.setNext(node);
        count++;
    }

    @Override
    public K max() {
        return last.getKey();
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public K floor(K key) {
        return null;
    }

    @Override
    public K ceiling(K key) {
        return null;
    }

    @Override
    public void delete(K key) {

    }

    @Override
    public int rank(K key) {
        return 0;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public K select(int k) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        return null;
    }
}
