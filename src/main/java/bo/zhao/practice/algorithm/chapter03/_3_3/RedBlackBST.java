package bo.zhao.practice.algorithm.chapter03._3_3;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/8/12
 */
public class RedBlackBST<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node<K, V> root;

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

    private void flipColors(Node<K, V> h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
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
