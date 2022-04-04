package leetcode.linkedlists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SwappingNodes {
    public ListNode swapNodes(ListNode head, int k) {
        var node = head;
        // First calculate the size and while we are at it, get the first element to swap.
        var size = 0;
        ListNode first = null;
        ListNode last = null;
        while (node != null) {
            size++;
            if (last != null)
                last = last.next;
            if (size == k) {
                first = node;
                last = head;
            }
            node = node.next;
        }

        var fVal = first.val;
        first.val = last.val;
        last.val = fVal;
        return head;
    }

    @Test
    void test1() {
        var head = ListNode.createList(1,2,3,4,5);
        ListNode.validateList(swapNodes(head, 2), 1,4,3,2,5);
    }

    @Test
    void test2() {
        var head = ListNode.createList(1,2,3,4,5);
        ListNode.validateList(swapNodes(head, 3), 1,2,3,4,5);
    }

    @Test
    void test3() {
        var head = ListNode.createList(1,2,3,4,5);
        ListNode.validateList(swapNodes(head, 4), 1,4,3,2,5);
    }

    @Test
    void test4() {
        var head = ListNode.createList(90, 100);
        ListNode.validateList(swapNodes(head, 2), 100, 90);
    }

}
