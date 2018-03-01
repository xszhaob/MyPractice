package bo.zhao.practice.algorithm.chapter01;

import java.util.Iterator;
import java.util.Random;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/3/1
 */
@SuppressWarnings("unchecked")
public class RandomBag<E> implements Bag<E>, Iterable<E> {

    private E[] arr = (E[]) new Object[2];
    private int n;
    private Random random;

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void add(E item) {
        if (n == arr.length) {
            resize(arr.length * 2);
        }
        arr[n] = item;
        n++;
    }

    @Override
    public Iterator<E> iterator() {
        shuffle();
        return new RandomBagIterator();
    }

    private void shuffle() {
        if (random == null) {
            random = new Random();
        }
        for (int i = arr.length; i > 1; i--) {
            swap(arr, i - 1, random.nextInt(i));
        }
    }

    private void swap(E[] arr, int n1, int n2) {
        E tmp = arr[n1];
        arr[n1] = arr[n2];
        arr[n2] = tmp;
    }

    private void resize(int length) {
        E[] temp = (E[]) new Object[length];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        arr = temp;
    }


    private class RandomBagIterator implements Iterator<E> {
        private int index = 0;
        private int count = n;
        @Override
        public boolean hasNext() {
            return index < count;
        }

        @Override
        public E next() {
            E e = arr[index];
            index++;
            return e;
        }
    }
}
