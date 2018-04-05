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

        ListNode head = new ListNode(0);
        head.next = l1;

        ListNode beforeCurrent = head;
        ListNode current = beforeCurrent.next;
        while (l2 != null) {
            while (current != null) {
                if (current.val > l2.val) {
                    beforeCurrent.next = new ListNode(l2.val);
                    beforeCurrent.next.next = current;

                    beforeCurrent = beforeCurrent.next;
                    break;
                }
                beforeCurrent = current;
                current = current.next;
            }
            // node2的值不大于node1最后一个节点
            if (current == null && beforeCurrent.val <= l2.val) {
                beforeCurrent.next = new ListNode(l2.val);
                current = beforeCurrent.next;
            }
            l2 = l2.next;
        }
        return head.next;
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
