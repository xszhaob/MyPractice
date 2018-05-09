package bo.zhao.practice.algorithm.chapter03;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/9
 */
public class SequentialSearchST<K, V> implements ST<K, V> {
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
        }
        return null;
    }

    @Override
    public void delete(K key) {
        Node<K, V> x = first;
        Node<K, V> last = null;
        while (x != null) {
            if (key.equals(x.getKey())) {
                // 第一个
                if (last == null) {
                    first = x.getNext();
                } else {
                    last.setNext(x.getNext());
                }
                count--;
                return;
            }
            last = x;
            x = x.getNext();
        }
    }

    @Override
    public boolean contains(K key) {
        Node<K, V> x = first;
        while (x != null) {
            if (key.equals(x.getKey())) {
                return true;
            }
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
        return null;
    }
}
