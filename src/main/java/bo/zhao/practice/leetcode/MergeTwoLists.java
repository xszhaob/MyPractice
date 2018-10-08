package bo.zhao.practice.leetcode;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/28
 */
public class MergeTwoLists {

    /**
     * 使用链表循环的方式解决
     */
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode beforeHead = new ListNode(0);
        ListNode current = beforeHead;

        ListNode node1 = l1;
        ListNode node2 = l2;
        while (true) {
            if (node1 == null) {
                current.next = node2;
                break;
            }
            if (node2 == null) {
                current.next = node1;
                break;
            }
            if (node1.val >= node2.val) {
                current.next = node2;
                node2 = node2.next;
            } else {
                current.next = node1;
                node1 = node1.next;
            }
            current = current.next;
        }
        return beforeHead.next;
    }

    /**
     * 使用链表递归的方式解决
     */
    private static ListNode mergeTwoListsV2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode head;
        if (l1.val < l2.val) {
            head = l1;
            head.next = mergeTwoListsV2(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoListsV2(l1, l2.next);
        }
        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode current = this;
            while (current != null) {
                sb.append(current.val).append(",");
                current = current.next;
            }
            return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(2);
        node1.next.next.next = new ListNode(2);
        node1.next.next.next.next = new ListNode(2);
        node1.next.next.next.next.next = new ListNode(2);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(1);
        node2.next.next = new ListNode(1);
        node2.next.next.next = new ListNode(4);
        node2.next.next.next.next = new ListNode(5);
        node2.next.next.next.next.next = new ListNode(6);

//        System.out.println(mergeTwoLists(node1, node2));
        System.out.println(mergeTwoListsV2(node1, node2));
    }
}
