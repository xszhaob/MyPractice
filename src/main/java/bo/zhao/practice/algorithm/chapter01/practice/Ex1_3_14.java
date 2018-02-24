package bo.zhao.practice.algorithm.chapter01.practice;

/**
 * Created by zhaobo on 2018/2/24.
 */
@SuppressWarnings("unchecked")
public class Ex1_3_14<E> {

    private E[] arr = (E[]) new Object[2];
    private int n;
    private int first;
    private int last;

    private void enqueue(E e) {
        if (arr.length == n) {
            resize(arr.length * 2);
        }
        arr[last++] = e;
        if (last == arr.length) {
            last = 0;
        }
        n++;
    }


    public E dequeue() {
        if (n == 0) {
            return null;
        }
        E e = arr[first];
        arr[first] = null;

        n--;

        first++;
        if (first == arr.length) {
            first = 0;
        }

        if (n > 0 && n <= arr.length / 4) {
            resize(arr.length / 2);
        }
        return e;
    }


    private void resize(int length) {
        E[] tmp = arr;
        arr = (E[]) new Object[length];
        for (int i = 0; i < n; i++) {
            arr[i] = tmp[(first + i) % tmp.length];
            first = 0;
            last = n;
        }
    }


    public static void main(String[] args) {
        Ex1_3_14<String> ex = new Ex1_3_14<>();
        ex.enqueue("a");
        ex.enqueue("b");
        ex.enqueue("c");
        ex.enqueue("d");

        System.out.println(ex.dequeue());
        System.out.println(ex.dequeue());
        ex.enqueue("e");
        ex.enqueue("f");
        System.out.println(ex.dequeue());
        System.out.println(ex.dequeue());
        System.out.println(ex.dequeue());
    }
}
