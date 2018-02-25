package bo.zhao.practice.algorithm.chapter01.practice;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/25
 */
public class Ex1_3_30 {

    public static void main(String[] args) {
        Node<String> first = new Node<>();
        first.item = "a";
        first.next = new Node<>();
        first.next.item = "b";
        first.next.next = new Node<>();
        first.next.next.item = "c";
        first.next.next.next = new Node<>();
        first.next.next.next.item = "d";

        Node<String> reverse = reverse(first);
        while (reverse != null) {
            System.out.println(reverse.item);
            reverse = reverse.next;
        }

        Node<String> node = recursionReverse(first);
        while (node != null) {
            System.out.println(node.item);
            node = node.next;
        }
    }


    private static Node<String> reverse(Node<String> first) {
        if (first == null) {
            return null;
        }
        Node<String> result = null;
        while (first != null) {
            Node<String> tmp = result;
            result = new Node<>();
            result.item = first.item;
            result.next = tmp;
            first = first.next;
        }

        return result;
    }

    private static Node<String> recursionReverse(Node<String> first) {
        if (first == null) {
            return null;
        }
        if (first.next == null) {
            return first;
        }

        Node<String> second = first.next;
        Node<String> rest = recursionReverse(second);
        second.next = first;
        first.next = null;
        return rest;
    }


    private static class Node<T> {
        private T item;
        private Node<T> next;
    }
}
