package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 *
 * Constraints:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */
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
        ListNode summation = null;
        int carry = 0;
        ListNode cursor = null;
        while (l1 != null && l2 != null) {
            int value = l1.val + l2.val + carry;
            carry = value / 10;
            value = value % 10;
            if (summation == null) {
                summation = new ListNode(value);
                cursor = summation;
            } else {
                cursor.next = new ListNode(value);
                cursor = cursor.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int value = l1.val + carry;
            carry = value / 10;
            value = value % 10;
            cursor.next = new ListNode(value);
            cursor = cursor.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int value = l1.val + carry;
            carry = value / 10;
            value = value % 10;
            cursor.next = new ListNode(value);
            cursor = cursor.next;
            l2 = l2.next;
        }
        if (carry > 0) {
            cursor.next = new ListNode(carry);
        }
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
