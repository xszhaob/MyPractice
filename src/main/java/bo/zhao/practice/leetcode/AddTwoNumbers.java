package bo.zhao.practice.leetcode;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/23
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultHead = new ListNode(0);
        ListNode tmp = resultHead;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;

            int sum = v1 + v2 + carry;
            carry = sum / 10;

            tmp.next = new ListNode(sum % 10);
            tmp = tmp.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;

        }

        if (carry > 0) {
            tmp.next = new ListNode(carry);
        }

        return resultHead.next;
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
            sb.append(current.val);
            while (current.next != null) {
                current = current.next;
                sb.append("->").append(current.val);
            }
            return sb.toString();
        }
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);

        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(6);
        node2.next.next = new ListNode(4);

        System.out.println(new AddTwoNumbers().addTwoNumbers(node1, node2));
    }
}
