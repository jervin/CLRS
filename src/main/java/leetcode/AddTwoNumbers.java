package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddTwoNumbers {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // https://leetcode.com/problems/add-two-numbers/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode summation = new ListNode(0);
        ListNode cursor = summation;
        // l1 + l2
        while( l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            l1 = l1.next;
            l2 = l2.next;
            if (sum > 9)
                carry = 1;
            else
                carry = 0;
            cursor.val = sum % 10;
            cursor.next = new ListNode(0);
            cursor = cursor.next;
        }
        while (l1 != null || carry > 0) {
            cursor.next = new ListNode(0);
            cursor = cursor.next;
            int sum = (l1 != null ? l1.val : 0) + carry;
            cursor.val = sum % 10;
            if (sum > 9)
                carry = 1;
            else
                carry = 0;
            l1 = l1 != null ? l1.next : null;
        }
        while (l2 != null || carry > 0) {
            cursor.next = new ListNode(0);
            cursor = cursor.next;
            int sum = (l2 != null ? l2.val : 0) + carry;
            cursor.val = sum % 10;
            if (sum > 9)
                carry = 1;
            else
                carry = 0;
            l2 = l2 != null ? l2.next : null;
        }
        if (carry > 0)
            cursor.val = carry;
        // Remove any leading zeros
        cursor = summation;
        while (cursor.next != null && cursor.next.next != null)
            cursor = cursor.next;
        if (cursor.next != null && cursor.next.val == 0)
            cursor.next = null;

        return summation;
    }

    @Test
    void test1() {
        val input1 = new ListNode(9);
        var cursor = input1;
        for (int i = 0; i < 6; i++) {
            cursor.next = new ListNode(9);
            cursor = cursor.next;
        }
        val input2 = new ListNode(9);
        cursor = input2;
        for (int i = 0; i < 3; i++) {
            cursor.next = new ListNode(9);
            cursor = cursor.next;
        }
        var expected = new ListNode(8);
        cursor = expected;
        cursor.next = new ListNode(9);
        cursor = cursor.next;
        cursor.next = new ListNode(9);
        cursor = cursor.next;
        cursor.next = new ListNode(0);
        cursor = cursor.next;
        cursor.next = new ListNode(0);
        cursor = cursor.next;
        cursor.next = new ListNode(0);
        cursor = cursor.next;
        cursor.next = new ListNode(1);

        var actual = addTwoNumbers(input1, input2);
        while(expected != null) {
            assertEquals(expected.val, actual.val);
            expected = expected.next;
            actual = actual.next;
        }
        assertTrue(expected == null && actual == null);
    }
}
