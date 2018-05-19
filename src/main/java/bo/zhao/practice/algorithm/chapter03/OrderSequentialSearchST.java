package bo.zhao.practice.algorithm.chapter03;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;

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
            count++;
            return;
        }
        Node<K, V> tmp = first;
        Node<K, V> previousNode = null;
        while (tmp != null) {
            int com = tmp.getKey().compareTo(key);
            // 替换原来key和value的值
            if (com == 0) {
                tmp.setKey(key);
                tmp.setValue(value);
                return;
            } else if (com > 0) {
                if (previousNode == null) {
                    previousNode = new Node<>(key, value);
                    previousNode.setNext(first);
                    first = previousNode;
                    count++;
                    return;
                } else {
                    Node<K, V> node = new Node<>(key, value);
                    node.setNext(tmp);
                    previousNode.setNext(node);
                    count++;
                    return;
                }
            }
            previousNode = tmp;
            tmp = tmp.getNext();
        }
        Node<K, V> node = new Node<>(key, value);
        last.setNext(node);
        last = node;
        count++;
    }

    @Override
    public K max() {
        return last.getKey();
    }

    @Override
    public V get(K key) {
        if (count == 0) {
            return null;
        }
        Node<K, V> tmp = first;

        while (tmp != null) {
            int com = tmp.getKey().compareTo(key);
            if (com == 0) {
                return tmp.getValue();
                // 如果大于0，说明后面的key比指定的key大，继续查找无意义
            } else if (com > 0) {
                break;
            }
            tmp = tmp.getNext();
        }
        return null;
    }

    /**
     * 小于等于key的最大值
     */
    @Override
    public K floor(K key) {
        if (count == 0) {
            return null;
        }
        // 小于最小值的情况
        if (key.compareTo(first.getKey()) < 0) {
            return null;
        }
        // 大于等于最大的值的情况
        if (key.compareTo(last.getKey()) >= 0) {
            return last.getKey();
        }
        Node<K, V> tmp = first;
        Node<K, V> last = null;
        while (tmp != null) {
            int com = tmp.getKey().compareTo(key);
            if (com == 0) {
                return tmp.getKey();
            } else if (com > 0) {
                if (last != null) {
                    return last.getKey();
                } else {
                    return null;
                }
            }
            last = tmp;
            tmp = tmp.getNext();
        }
        return null;
    }

    /**
     * 大于等于key的最小值
     *
     * @param key
     * @return
     */
    @Override
    public K ceiling(K key) {
        if (isEmpty()) {
            return null;
        }
        if (key.compareTo(first.getKey()) <= 0) {
            return first.getKey();
        }
        Node<K, V> tmp = first;
        while (tmp != null) {
            int com = tmp.getKey().compareTo(key);
            if (com >= 0) {
                return tmp.getKey();
            }
            tmp = tmp.getNext();
        }
        return null;
    }

    @Override
    public void delete(K key) {
        if (isEmpty()) {
            return;
        }
        // 比最大值大 或 比最小值小
        if (key.compareTo(last.getKey()) > 0 || key.compareTo(first.getKey()) < 0) {
            return;
        }
        // 等于最小值
        if (key.compareTo(first.getKey()) == 0) {
            first = first.getNext();
            // 队列中只有一个元素
            if (count == 1) {
                last = null;
            }
            count--;
            return;
        }

        Node<K, V> tmp = first.getNext();
        Node<K, V> last = first;
        while (tmp != null) {
            int compareTo = tmp.getKey().compareTo(key);
            // 确定找不到对应的值
            if (compareTo > 0) {
                break;
            }
            // 有对应的值
            if (compareTo == 0) {
                last.setNext(tmp.getNext());
                count--;
                break;
            }
            last = tmp;
            tmp = tmp.getNext();
        }
    }

    @Override
    public int rank(K key) {
        int n = 0;
        Node<K, V> tmp = first;
        while (tmp != null) {
            int compareTo = tmp.getKey().compareTo(key);
            if (compareTo >= 0) {
                return n;
            }
            n++;
            tmp = tmp.getNext();
        }
        return n;
    }

    @Override
    public boolean contains(K key) {
        if (isEmpty()) {
            return false;
        }
        Node<K, V> tmp = first;

        while (tmp != null) {
            if (tmp.getKey().compareTo(key) == 0) {
                return true;
            }
            tmp = tmp.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public K select(int k) {
        if (k < 0 || k >= count) {
            throw new IndexOutOfBoundsException("count = " + count + ", index = " + k);
        }
        int n = 0;
        Node<K, V> tmp = first;
        while (n < k) {
            tmp = tmp.getNext();
            n++;
        }
        return tmp.getKey();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        LinkedQueue<K> queue = new LinkedQueue<>();
        if (count == 0 || lo.compareTo(hi) > 0) {
            return queue;
        }
        Node<K, V> tmp = first;
        while (tmp != null) {
            int endCom = tmp.getKey().compareTo(hi);
            if (endCom > 0) {
                break;
            }
            int beginCom = tmp.getKey().compareTo(lo);
            if (beginCom < 0) {
                continue;
            }
            queue.enqueue(tmp.getKey());
            tmp = tmp.getNext();
        }
        return queue;
    }
}
