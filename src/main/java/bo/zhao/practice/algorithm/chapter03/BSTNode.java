package bo.zhao.practice.algorithm.chapter03;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/27
 */
public class BSTNode<K, V> {
    private K key;

    private V value;

    /**
     * 左子结点，左子结点的key比父结点小
     */
    private BSTNode<K, V> left;

    /**
     * 右子结点，右子结点的key比父结点大
     */
    private BSTNode<K, V> right;

    /**
     * 以该结点为根的子树中的结点总数
     */
    private int count;

    /**
     * 当前树的高度
     */
    private int height;

    public BSTNode() {
    }

    public BSTNode(K key, V value, int count) {
        this.key = key;
        this.value = value;
        this.count = count;
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

    public BSTNode<K, V> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<K, V> left) {
        this.left = left;
    }

    public BSTNode<K, V> getRight() {
        return right;
    }

    public void setRight(BSTNode<K, V> right) {
        this.right = right;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
