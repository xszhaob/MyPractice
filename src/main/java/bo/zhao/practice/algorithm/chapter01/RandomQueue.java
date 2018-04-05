package bo.zhao.practice.algorithm.chapter01;

import java.util.Iterator;
import java.util.Random;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/3/2
 */
@SuppressWarnings("unchecked")
public class RandomQueue<E> implements Iterable<E> {

    private E[] arr = (E[]) new Object[2];
    private int n;
    private Random random;

    public void enqueue(E item) {
        if (arr.length == n) {
            resize(arr.length * 2);
        }
        arr[n] = item;
        n++;
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        if (random == null) {
            random = new Random();
        }
        swap(arr, n - 1, random.nextInt(n));
        E item = arr[n - 1];
        n--;
        return item;
    }

    public E sample() {
        if (isEmpty()) {
            return null;
        }
        E item = arr[random.nextInt(n - 1)];
        n--;
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private void swap(E[] arr, int idx1, int idx2) {
        E item = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = item;
    }

    private void resize(int length) {
        E[] tmp = (E[]) new Object[length];
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        arr = tmp;
    }

    public static void main(String[] args) {
        RandomQueue<String> queue = new RandomQueue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
