package leetcode.linkedlists;

import lombok.val;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    public static ListNode createList(int... values) {
        ListNode head = null;
        ListNode cursor = null;
        for (int i = 0; i < values.length; i++) {
            if (head == null) {
                head = new ListNode(values[i]);
                cursor = head;
                continue;
            }
            cursor.next = new ListNode(values[i]);
            cursor = cursor.next;
        }
        return head;
    }
}
