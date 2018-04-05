package bo.zhao.practice.leetcode;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/28
 */
public class RemoveNthFromEnd {

    private static ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }

        if (length == n) {
            return head.next;
        }

        int i = length - n;
        ListNode beforeRemoveNode = head;
        while (--i > 0) {
            beforeRemoveNode = beforeRemoveNode.next;
        }

        beforeRemoveNode.next = beforeRemoveNode.next.next;
        return head;
    }



    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode current = this;
            while (current != null) {
                sb.append(current.val).append(",");
                current = current.next;
            }
            return sb.toString();
        }
    }


    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next = new ListNode(6);

        System.out.println(removeNthFromEnd(node, 6));
    }
}
