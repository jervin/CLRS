package leetcode.linkedlists;

import org.junit.jupiter.api.Test;

import static leetcode.linkedlists.ListNode.createList;
import static leetcode.linkedlists.ListNode.validateList;
import static org.junit.jupiter.api.Assertions.*;

public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;
        var cursor = head;
        int n = 1;
        while (cursor.next != null) {
            cursor = cursor.next;
            n++;
        }
        cursor.next = head; // make this a ring
        ListNode newTail = head;
        k = k % n;
        // The n - k - 1 to get the new tail is the trick, modulo arithmetic
        for (int i = 0; i < (n - k - 1); i++)
            newTail = newTail.next;
        var newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }

    @Test
    void test1() {
        validateList(rotateRight(createList(0,1,2),4), 2,0,1);
    }

    @Test
    void test4() {
        validateList(rotateRight(createList(0,1,2),5), 1,2,0);
    }

    @Test
    void test2() {
        validateList(rotateRight(createList(1,2),0), 1,2);
    }

    @Test
    void test3() {
        validateList(rotateRight(createList(1,2),1), 2,1);
    }
}
