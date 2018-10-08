package bo.zhao.practice.leetcode;

import java.util.ArrayList;
import java.util.List;

public class RemoveNthNodeFromEndOfList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        List<ListNode> listNodes = new ArrayList<>();
        listNodes.add(head);
        ListNode next = head.next;
        while (next != null) {
            listNodes.add(next);
            next = next.next;
        }
        if (listNodes.size() == n) {
            return head.next;
        }
        ListNode listNode = listNodes.get(listNodes.size() - n - 1);
        listNode.next = listNode.next.next;
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
