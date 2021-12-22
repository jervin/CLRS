package leetcode.linkedlists;

import lombok.val;
import org.junit.jupiter.api.Test;

public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return null;

        ListNode evenHead = null;
        ListNode even = null;
        ListNode odd = head;

        int index = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            if (index%2 == 0) {
                if (evenHead == null)
                    evenHead = node;
                else
                    even.next = node;
                even = node;
            }
            else {
                odd.next = node;
                if (node != null)
                    odd = node;
            }
            index++;
        }
        odd.next = evenHead;
        return head;
    }


    @Test
    void test1() {
        val input = ListNode.createList(1,2,3,4,5);
        oddEvenList(input);
    }

    @Test
    void test2() {
        val input = ListNode.createList(1,2,3,4,5,6,7,8);
        oddEvenList(input);
    }
}
