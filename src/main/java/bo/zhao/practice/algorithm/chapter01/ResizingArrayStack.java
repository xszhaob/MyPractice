package bo.zhao.practice.algorithm.chapter01;

import java.util.Iterator;

/**
 * Created by zhaobo on 2018/2/22.
 */
@SuppressWarnings("unchecked")
public class ResizingArrayStack<E> implements Iterable<E> {

    private E[] arr = (E[]) new Object[1];
    private int n = 0;


    public boolean isEmpty() {
        return n == 0;
    }

    public void push(E e) {
        if (n == arr.length) {
            resize(arr.length * 2);
        }
        arr[n++] = e;
    }

    public E pop() {
        E e = arr[--n];
        arr[n] = null;
        if (n > 0 && n == arr.length / 4) {
            resize(arr.length / 2);
        }
        return e;
    }

    @Override
    public Iterator<E> iterator() {
        return new ReverseArrayIterator();
    }


    private void resize(int length) {
        E[] temp = (E[]) new Object[length];
        System.arraycopy(arr, 0, temp, 0, arr.length);

        arr = temp;
    }


    private class ReverseArrayIterator implements Iterator<E> {

        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public E next() {
            return arr[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();

        stack.push("hello");
        stack.push("world");
        stack.push("bo");
        stack.push("zhao");

        for (String s : stack) {
            System.out.println(s);
        }
    }
}
