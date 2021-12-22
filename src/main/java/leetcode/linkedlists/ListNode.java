package leetcode.linkedlists;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    public static void validateList(ListNode head, int... values) {
        var cursor = head;
        for (int value : values) {
            assertNotNull(cursor);
            assertEquals(value, cursor.val);
            cursor = cursor.next;
        }
        assertNull(cursor);
    }
}
