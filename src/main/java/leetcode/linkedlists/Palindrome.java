package leetcode.linkedlists;

import lombok.val;
import org.junit.jupiter.api.Test;

import static leetcode.linkedlists.ListNode.createList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Palindrome {
    int size(ListNode head) {
        ListNode node = head;
        int count = 1;
        while (node.next != null) {
            count++;
            node = node.next;
        }
        return count;
    }
    ListNode createReverseList(ListNode head, int endIndex) {
        ListNode reverse = new ListNode(head.val);
        ListNode cursor = head;
        for (int i = 1; i < endIndex; i++) {
            reverse = new ListNode(cursor.next.val, reverse);
            cursor = cursor.next;
        }
        return reverse;
    }
    public boolean isPalindrome(ListNode head) {
        if (head.next == null)
            return true;
        int size = size(head);
        int mid = size / 2;
        boolean even = size % 2 == 0 ? true : false;
        ListNode reversed = createReverseList(head, mid);
        // Get to the second half of the list
        ListNode cursor = head;
        for (int i = 1; i <= mid; i++) {
            cursor = cursor.next;
        }
        if (!even)
            cursor = cursor.next;
        while(cursor != null) {
            if (cursor.val != reversed.val)
                return false;
            cursor = cursor.next;
            reversed = reversed.next;
        }
        return true;
    }

    @Test
    void testCreateReverseList1() {
        val input = createList(1,2);
        val reversed = createReverseList(input, 1);
        assertEquals(1, reversed.val);
        assertNull(reversed.next);
    }
    @Test
    void testCreateReverseList2() {
        val input = createList(1,2,3,4,5);
        val reversed = createReverseList(input, 2);
        assertEquals(2, reversed.val);
        assertEquals(1, reversed.next.val);
        assertNull(reversed.next.next);
    }
    @Test
    void test1() {
        val input = createList(1,2,2,1);
        assertTrue(isPalindrome(input));
    }
    @Test
    void test2() {
        val input = createList(1,2);
        assertFalse(isPalindrome(input));
    }
    @Test
    void test3() {
        val input = createList(1,2,3,2,1);
        assertTrue(isPalindrome(input));
    }
    @Test
    void test4() {
        val input = createList(1,2,3,1,1);
        assertFalse(isPalindrome(input));
    }
    @Test
    void test5() {
        val input = createList(1,2,3,4,5,6,5,4,3,2,1);
        assertTrue(isPalindrome(input));
    }
    @Test
    void test6() {
        // Size is 87
        val input = createList(8,0,7,1,7,7,9,7,5,2,9,1,7,3,7,0,6,5,1,7,7,9,3,8,1,5,7,7,8,4,0,9,3,7,3,4,5,7,4,8,8,5,8,9,8,5,8,8,4,7,5,4,3,7,3,9,0,4,8,7,7,5,1,8,3,9,7,7,1,5,6,0,7,3,7,1,9,2,5,7,9,7,7,1,7,0,8);
        assertTrue(isPalindrome(input));
    }
}
