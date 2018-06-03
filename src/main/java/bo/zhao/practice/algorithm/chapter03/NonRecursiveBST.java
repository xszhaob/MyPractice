package bo.zhao.practice.algorithm.chapter03;

import bo.zhao.practice.algorithm.chapter01.LinkedQueue;
import bo.zhao.practice.algorithm.chapter01.LinkedStack;

/**
 * Created by zhaobo on 2018/6/3.
 */
public class NonRecursiveBST<K extends Comparable<K>, V> extends BST<K, V> {

    @Override
    public V get(K key) {
        BSTNode<K, V> node = root;
        while (node != null) {
            int com = key.compareTo(node.getKey());
            if (com == 0) {
                return node.getValue();
            } else if (com > 0) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }
        return null;
    }


    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new BSTNode<>(key, value, 1);
            return;
        }
        LinkedStack<BSTNode<K, V>> stack = new LinkedStack<>();
        BSTNode<K, V> node = root;
        BSTNode<K, V> parentNode = null;
        while (node != null) {
            parentNode = node;
            stack.push(node);
            int com = key.compareTo(node.getKey());
            if (com == 0) {
                node.setValue(value);
                return;
            } else if (com > 0) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }
        int com = key.compareTo(parentNode.getKey());
        if (com < 0) {
            parentNode.setLeft(new BSTNode<>(key, value, 1));
        } else {
            parentNode.setRight(new BSTNode<>(key, value, 1));
        }
        for (BSTNode<K, V> tmpNode : stack) {
            tmpNode.setCount(size(tmpNode.getLeft()) + size(tmpNode.getRight()) + 1);
            tmpNode.setHeight(1 + Math.max(height(tmpNode.getLeft()), height(tmpNode.getRight())));
        }
    }


    @Override
    public Iterable<K> keys() {
        LinkedQueue<K> queue = new LinkedQueue<>();
        LinkedStack<BSTNode<K, V>> stack = new LinkedStack<>();
        if (root == null) {
            return queue;
        }
        BSTNode<K, V> node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                queue.enqueue(node.getKey());
                node = node.getRight();
            }
        }
        return queue;
    }
}
