package leetcode.linkedlists;

import lombok.val;
import org.junit.jupiter.api.Test;

public class ReverseLinkedList {
    // https://leetcode.com/explore/challenge/card/september-leetcoding-challenge-2021/636/week-1-september-1st-september-7th/3966/

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        public String toString() { return "node: " + val + " -> " + next; }
    }

    private void putAtEnd(ListNode cursor, ListNode head) {
        while(cursor.next != null)
            cursor = cursor.next;
        cursor.next = head;
        head.next = null;
    }

    public ListNode reverseListBad(ListNode head) {
        if (head == null)
            return null;
        ListNode cursor = reverseListBad(head.next);
        if (cursor == null)
            return head;
        putAtEnd(cursor, head);
        //head.next = cursor.next != null ? cursor.next.next : null;
        return cursor;
    }



    public ListNode reverseRecursively(ListNode head) {
        if (head == null)
            return null;
        ListNode reversedHead = new ListNode(head.val);
        return reverseRecursively(head.next, reversedHead);
    }

    private ListNode reverseRecursively(ListNode node, ListNode reversedHead) {
        if (node == null)
            return reversedHead;
        return reverseRecursively(node.next, new ListNode(node.val, reversedHead));
    }


    public ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        ListNode reversedHead = new ListNode(head.val);
        while (head.next != null) {
            head = head.next;
            reversedHead = new ListNode(head.val, reversedHead);
        }
        return reversedHead;
    }

    public ListNode reverseListInPlace(ListNode head) {
        // Going to try this in place...
        if (head == null)
            return null;
        ListNode reversed = head;
        ListNode node = head.next;
        ListNode next = node != null ? node.next : null;
        while (node != null) {
            head.next = next;
            node.next = reversed;
            reversed = node;
            node = next;
            next = node != null ? node.next : null;
        }
        return reversed;
    }

    @Test
    void testReverseList() {
        val solution = new ReverseLinkedList();
        val head = new ListNode(1);
        var cursor = head;
        for (int i = 2; i <= 5; i++) {
            cursor.next = new ListNode(i);
            cursor = cursor.next;
        }
        val answer = solution.reverseListInPlace(head);
    }
}
