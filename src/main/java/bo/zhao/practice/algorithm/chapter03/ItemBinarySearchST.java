package bo.zhao.practice.algorithm.chapter03;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by zhaobo on 2018/5/17.
 */
public class ItemBinarySearchST<K extends Comparable<K>, V> implements SortST<K, V> {

    private Item<K, V>[] items;

    private Item<K, V>[] aux;

    public ItemBinarySearchST() {
    }

    public ItemBinarySearchST(Item<K, V>[] items) {
        this.items = mergeSort(items);
    }

    @Override
    public K min() {
        return null;
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public K max() {
        return null;
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


    @SuppressWarnings("unchecked")
    private Item<K, V>[] mergeSort(Item<K, V>[] items) {
        aux = (Item<K, V>[]) new Item[items.length];
        sort(items, 0, items.length - 1);
        return items;
    }

    private void sort(Item<K, V>[] items, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(items, lo, mid);
        sort(items, mid + 1, hi);
        merge(items, lo, mid, hi);
    }

    private void merge(Item<K, V>[] items, int lo, int mid, int hi) {
        System.arraycopy(items, lo, aux, lo, hi - lo + 1);

        int m = lo;
        int n = mid + 1;
        for (int i = lo; i <= hi; i++) {
            if (m > mid) {
                items[i] = aux[n++];
            } else if (n > hi) {
                items[i] = aux[m++];
                // 前半部分大
            } else if (aux[m].getKey().compareTo(aux[n].getKey()) > 0) {
                items[i] = aux[n++];
            } else {
                items[i] = aux[m++];
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        Item<String, Integer>[] items = (Item<String, Integer>[])new Item[9];
        items[0] = new Item<>("9", 9);
        items[1] = new Item<>("8", 8);
        items[2] = new Item<>("0", 0);
        items[3] = new Item<>("6", 6);
        items[4] = new Item<>("5", 5);
        items[5] = new Item<>("7", 7);
        items[6] = new Item<>("1", 1);
        items[7] = new Item<>("2", 2);
        items[8] = new Item<>("3", 3);
        Item<K, V>[] items1 = mergeSort((Item<K, V>[]) items);
        System.out.println(Arrays.toString(items1));
    }


}
