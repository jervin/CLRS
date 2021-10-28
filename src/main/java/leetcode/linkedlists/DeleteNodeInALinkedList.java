package leetcode.linkedlists;

import lombok.val;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/603/
 */
public class DeleteNodeInALinkedList {
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
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0)
            return head;
        if (head.next == null)
            return null;
        ListNode prev = null;
        int count = 1;
        ListNode node = head;
        while(node.next != null) {
            count++;
            if (count > n) {
                prev = prev != null ? prev.next : head;
            }
            node = node.next;
        }
        if (prev == null)
            return head.next;
        prev.next = prev.next != null && prev.next.next != null ? prev.next.next : null;
        return head;
    }

    @Test
    void test1() {
        val head = new ListNode(1);
        head.next = new ListNode(2);
        removeNthFromEnd(head, 2);
    }

    @Test
    void test2() {
        val head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        val returned = removeNthFromEnd(head, 2);
    }
}
