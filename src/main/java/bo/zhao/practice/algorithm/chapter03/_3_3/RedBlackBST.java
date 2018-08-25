package bo.zhao.practice.algorithm.chapter03._3_3;

import bo.zhao.practice.algorithm.chapter03.SortST;

import java.util.NoSuchElementException;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/8/12
 */
public class RedBlackBST<K extends Comparable<K>, V> implements SortST<K, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node<K, V> root;

    @Override
    public K min() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return min(root).key;
    }

    @Override
    public K max() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls max() with empty symbol table");
        }
        return max(root).key;
    }

    private Node<K, V> max(Node<K, V> node) {
        if (node.right == null) {
            return node;
        } else {
            return max(node.right);
        }
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
    public int rank(K key) {
        return 0;
    }

    @Override
    public K select(int k) {
        return null;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        return null;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        if (!contains(key)) {
            return;
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = delete(root, key);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node<K, V> delete(Node<K, V> node, K key) {
        if (key.compareTo(node.key) < 0) {
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = delete(node.left, key);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }
            if (key.compareTo(node.key) == 0 && node.right == null) {
                return null;
            }
            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }
            if (key.compareTo(node.key) == 0) {
                Node<K, V> x = min(node.right);
                node.key = x.key;
                node.value = x.value;
                node.right = deleteMin(node.right);
            } else {
                node.right = delete(node.right, key);
            }
        }
        return balance(node);
    }

    private Node<K, V> min(Node<K, V> node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    @Override

    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    /**
     * 删除最小值
     */
    @Override
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("BST underflow");
        }
        // 根节点是2-结点
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
    }

    /**
     * 删除最大值
     */
    @Override
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("BST underflow");
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
    }

    private Node<K, V> deleteMax(Node<K, V> node) {
        if (isRed(node.left)) {
            node = rotateRight(node);
        }
        if (node.right == null) {
            return null;
        }
        if (!isRed(node.right) && !isRed(node.right.left)) {
            node = moveRedRight(node);
        }

        node.right = deleteMax(node.right);

        return balance(node);
    }

    /**
     * Assuming that h is red and both h.right and h.right.left
     * are black, make h.right or one of its children red.
     */
    private Node<K, V> moveRedRight(Node<K, V> node) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    @Override
    public int size(K lo, K hi) {
        return 0;
    }

    @Override
    public Iterable<K> keys() {
        return null;
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null) {
            return null;
        }
        // 当前结点是2-结点
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return balance(node);
    }

    /**
     * Assuming that h is red and both h.left and h.left.left
     * are black, make h.left or one of its children red.
     */
    private Node<K, V> moveRedLeft(Node<K, V> node) {
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    /**
     * restore red-black tree invariant
     */
    private Node<K, V> balance(Node<K, V> node) {
        if (isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {

        if (node == null) {
            // 标准插入，父结点永远都是红色
            return new Node<>(key, value, 1, RED);
        }
        int com = key.compareTo(node.key);
        if (com > 0) {
            node.right = put(node.right, key, value);
        } else if (com < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }


    private boolean isRed(Node<K, V> node) {
        return node != null && node.color == RED;
    }

    /**
     * flip the colors of a node and its two children
     */
    private void flipColors(Node<K, V> h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private Node<K, V> rotateLeft(Node<K, V> h) {
        Node<K, V> x = h.right;
        h.right = x.left;
        x.left = h;

        // 颜色转换
        x.color = h.color;
        h.color = RED;

        // 子结点总数
        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);

        return x;
    }

    private Node<K, V> rotateRight(Node<K, V> h) {
        // 结点转换
        Node<K, V> x = h.left;
        h.left = x.right;
        x.right = h;

        // 颜色分类
        x.color = h.color;
        h.color = RED;

        // 子结点
        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);

        return x;
    }

    private int size(Node<K, V> node) {
        return 0;
    }


    private static class Node<K, V> {

        public Node() {
        }

        public Node(K key, V value, int n, boolean color) {
            this.key = key;
            this.value = value;
            this.n = n;
            this.color = color;
        }

        /**
         * 键
         */
        private K key;
        /**
         * 值
         */
        private V value;
        /**
         * 左子树
         */
        private Node<K, V> left;
        /**
         * 右子树
         */
        private Node<K, V> right;
        /**
         * 这棵子树中的结点总数
         */
        private int n;
        /**
         * 由其父结点指向它的链接的颜色
         */
        private boolean color;
    }
}
