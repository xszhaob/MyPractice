package bo.zhao.practice.leetcode;

import bo.zhao.practice.designpattern.builder.Packing;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/2/23
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n = 0;
        int count1 = l1.val;
        int count2 = l2.val;
        ListNode tempL1 = l1.next;
        ListNode tempL2 = l2.next;
        while (tempL1 != null) {
            int pow = (int) Math.pow(10, ++n);
            count1 += tempL1.val * pow;
            count2 += tempL2.val * pow;
            tempL1 = tempL1.next;
            tempL2 = tempL2.next;
        }


        int sum = count1 + count2;
        System.out.println("sum = " + sum);
        ListNode result = new ListNode(sum % 10);
        ListNode tmp = result;
        int val = sum / 10;
        while (val > 0) {
            tmp.next = new ListNode(val % 10);
            tmp = tmp.next;
            val = val / 10;
        }

        return result;
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
        node1.next.next = new ListNode(3);

        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(6);
        node2.next.next = new ListNode(4);

        System.out.println(new AddTwoNumbers().addTwoNumbers(node1, node2));
    }
}
