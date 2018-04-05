package bo.zhao.practice.leetcode;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/3/1
 */
public class MergeKLists {

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        ListNode first = null;
        int n = 0;
        for (ListNode list1 : lists) {
            n++;
            if (list1 != null) {
                first = list1;
                break;
            }
        }
        if (first == null) {
            return null;
        }
        ListNode head = new ListNode(0);
        head.next = first;
        for (int i = n; i < lists.length; i++) {
            ListNode list = lists[i];
            if (list == null) {
                continue;
            }
            ListNode beforeCurrent = head;
            ListNode current = beforeCurrent.next;
            while (list != null) {
                while (current != null) {
                    if (current.val >= list.val) {
                        beforeCurrent.next = new ListNode(list.val);
                        beforeCurrent.next.next = current;
                        current = beforeCurrent.next;
                        break;
                    }
                    beforeCurrent = current;
                    current = current.next;
                }
                if (current == null && beforeCurrent.val < list.val) {
                    beforeCurrent.next = new ListNode(list.val);
                    current = beforeCurrent.next;
                }
                list = list.next;
            }

        }
        return head.next;
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
//        node1.next = new ListNode(2);
//        node1.next.next = new ListNode(2);
//        node1.next.next.next = new ListNode(2);
//        node1.next.next.next.next = new ListNode(2);
//        node1.next.next.next.next.next = new ListNode(2);

        ListNode node2 = new ListNode(1);
//        node2.next = new ListNode(1);
//        node2.next.next = new ListNode(1);
//        node2.next.next.next = new ListNode(4);
//        node2.next.next.next.next = new ListNode(5);
//        node2.next.next.next.next.next = new ListNode(14);

        ListNode node3 = new ListNode(-5);
//        node3.next = new ListNode(-4);
//        node3.next.next = new ListNode(9);
//        node3.next.next.next = new ListNode(10);
//        node3.next.next.next.next = new ListNode(11);
//        node3.next.next.next.next.next = new ListNode(12);

        ListNode[] listNodes = new ListNode[]{node1, node2, node3};
        System.out.println(mergeKLists(listNodes));
    }
}
