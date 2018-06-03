package bo.zhao.practice.algorithm.chapter03;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/27
 */
public class BST<K extends Comparable<K>, V> implements SortST<K, V> {
    protected BSTNode<K, V> root;

    @Override
    public void put(K key, V value) {
        if (value == null) {
            delete(key);
        }
        root = put(root, key, value);
    }

    private BSTNode<K, V> put(BSTNode<K, V> node, K key, V value) {
        // 已经没有结点了
        if (node == null) {
            return new BSTNode<>(key, value, 1);
        }

        // **************还存在结点****************
        int com = key.compareTo(node.getKey());
        // 当前结点等于key
        if (com == 0) {
            node.setValue(value);
        } else if (com > 0) {
            // key大于当前结点
            node.setRight(put(node.getRight(), key, value));
        } else if (com < 0) {
            // key小于当前结点
            node.setLeft(put(node.getLeft(), key, value));
        }
        refreshNodeFeature(node);
        return node;
    }

    @Override
    public K min() {
        if (root == null) {
            return null;
        }
        return min(root).getKey();
    }

    private BSTNode<K, V> min(BSTNode<K, V> node) {
        if (node.getLeft() == null) {
            return node;
        }
        return min(node.getLeft());
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    public V get(BSTNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int com = key.compareTo(node.getKey());
        if (com > 0) {
            return get(node.getRight(), key);
        } else if (com < 0) {
            return get(node.getLeft(), key);
        } else {
            return node.getValue();
        }
    }

    @Override
    public K max() {
        if (root == null) {
            return null;
        }
        return max(root).getKey();
    }

    private BSTNode<K, V> max(BSTNode<K, V> node) {
        if (node.getRight() == null) {
            return node;
        }
        return max(node.getRight());
    }


    @Override
    public void deleteMin() {
        if (root == null) {
            return;
        }
        root = deleteMin(root);
    }

    private BSTNode<K, V> deleteMin(BSTNode<K, V> node) {
        if (node.getLeft() == null) {
            return node.getRight();
        }
        node.setLeft(deleteMin(node.getLeft()));
        refreshNodeFeature(node);
        return node;
    }

    @Override
    public void deleteMax() {
        if (root == null) {
            return;
        }
        root = deleteMax(root);
    }

    private BSTNode<K, V> deleteMax(BSTNode<K, V> node) {
        if (node.getRight() == null) {
            return node.getLeft();
        }
        node.setRight(deleteMax(node.getRight()));
        refreshNodeFeature(node);
        return node;
    }

    @Override
    public void delete(K key) {
        root = delete(root, key);
    }

    private BSTNode<K, V> delete(BSTNode<K, V> node, K key) {
        // 如果等于null，说明已经过滤到最后一个节点，没有可删除的数据
        if (node == null) {
            return null;
        }

        int com = key.compareTo(node.getKey());
        if (com > 0) {
            // 递归调用，更新右结点的值
            node.setRight(delete(node.getRight(), key));
        } else if (com < 0) {
            // 递归调用，更新左结点的值
            node.setLeft(delete(node.getLeft(), key));
        } else {
            // 当前key正是需要删除的key。

            // 如果右结点没有数据，直接返回左结点的数据即可
            if (node.getRight() == null) {
                return node.getLeft();
            }
            // 如果左结点没有数据，那么直接返回右结点数据即可
            if (node.getLeft() == null) {
                return node.getRight();
            }
            /*
            左右结点都有值的情况
            1）将指向即将被删除的结点node的链接保存为tmp；
            2）找到node的右结点中的最小结点并赋值给node；
            3）将node的右结点指向deleteMin(tmp.getRight())；
            4）将node的左结点指向tmp的左结点
             */
            BSTNode<K, V> tmp = node;
            node = min(node.getRight());
            node.setRight(deleteMin(tmp.getRight()));
            node.setLeft(tmp.getLeft());
        }
        refreshNodeFeature(node);
        return node;
    }

    @Override
    public K floor(K key) {
        BSTNode<K, V> floor = floor(root, key);
        if (floor == null) {
            return null;
        }
        return floor.getKey();
    }

    private BSTNode<K, V> floor(BSTNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int com = key.compareTo(node.getKey());
        if (com == 0) {
            return node;
        }
        BSTNode<K, V> floor;
        // 找更小的值
        if (com < 0) {
            return floor(node.getLeft(), key);
        } else {
            // 找更大的值
            floor = floor(node.getRight(), key);
            // 没有更大的值，就用当前值
            if (floor == null) {
                return node;
            } else {
                // 使用更大的值
                return floor;
            }
        }

    }

    @Override
    public K ceiling(K key) {
        BSTNode<K, V> ceiling = ceiling(root, key);
        if (ceiling == null) {
            return null;
        }
        return ceiling.getKey();
    }

    private BSTNode<K, V> ceiling(BSTNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int com = key.compareTo(node.getKey());
        if (com == 0) {
            return node;
        }
        if (com > 0) {
            return ceiling(node.getRight(), key);
        } else {
            BSTNode<K, V> ceiling = ceiling(node.getLeft(), key);
            if (ceiling == null) {
                return node;
            } else {
                return ceiling;
            }
        }
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public int rank(K key) {
        return rank(root, key);
    }

    private int rank(BSTNode<K, V> node, K key) {
        if (node == null) {
            return 0;
        }
        // key和当前结点的key相等，rank的值等于左边子结点数量
        int com = key.compareTo(node.getKey());
        if (com == 0) {
            return size(node.getLeft());
        }
        // key大于当前结点，rank的值等于当前结点和左边子结点的数量，以及右边小于key的子结点的数量
        if (com > 0) {
            return 1 + rank(node.getRight(), key) + size(node.getLeft());
        } else {
            // key小于当前结点，rank的等于当前结点的子结点中小于key的结点数量
            return rank(node.getLeft(), key);
        }
    }

    @Override
    public K select(int k) {
        return select(root, k);
    }

    private K select(BSTNode<K, V> node, int k) {
        if (node == null) {
            return null;
        }
        int t = size(node.getLeft());
        if (t == k) {
            return node.getKey();
        } else if (t < k) {
            return select(node.getRight(), k - t - 1);
        } else {
            return select(node.getLeft(), k);
        }
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        LinkedQueue<K> queue = new LinkedQueue<K>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(BSTNode<K, V> node, LinkedQueue<K> queue, K lo, K hi) {
        if (node == null) {
            return;
        }
        int comLo = lo.compareTo(node.getKey());
        int comHi = hi.compareTo(node.getKey());
        if (comLo < 0) {
            keys(node.getLeft(), queue, lo, hi);
        }
        if (comLo <= 0 && comHi >= 0) {
            queue.enqueue(node.getKey());
        }
        if (comHi > 0) {
            keys(node.getRight(), queue, lo, hi);
        }
    }

    protected int size(BSTNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return node.getCount();
    }

    /**
     * 树的高度，只有一个节点的树，高度是0。
     * 如果没有结点，高度就是-1。
     */
    public int height() {
        return height(root);
    }

    public int height(BSTNode<K, V> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    public int quickHeight() {
        if (isEmpty()) {
            return -1;
        }
        return root.getHeight();
    }

    protected void refreshNodeFeature(BSTNode<K, V> node) {
        node.setCount(size(node.getLeft()) + size(node.getRight()) + 1);
        node.setHeight(1 + Math.max(height(node.getRight()), height(node.getLeft())));
    }
}
