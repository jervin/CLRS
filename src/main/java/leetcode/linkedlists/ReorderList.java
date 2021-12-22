package leetcode.linkedlists;

import org.junit.jupiter.api.Test;

import java.util.Stack;

public class ReorderList {
    public void reorderList(ListNode head) {
       var stack = new Stack<ListNode>();
       var cursor = head;
       while (cursor != null) {
           stack.push(cursor);
           cursor = cursor.next;
       }
       cursor = head;
       while (!stack.empty()) {
           var next = cursor.next;
           var end = stack.pop();
           if (next == end) {
               // This catches the even case
               next.next = null;
               break;
           }
           cursor.next = end;
           end.next = next;
           cursor = next;
           // Need to figure out if we are done here or not...
           if (stack.empty())
               continue;
           if (cursor == stack.peek()) { // Odd number of elements
               cursor.next = null;
               break;
           }
       }
    }

    @Test
    void test1() {
        var input = ListNode.createList(1);
        reorderList(input);
        ListNode.validateList(input, 1); // expecting 1
    }
    @Test
    void test2() {
        var input = ListNode.createList(1,2);
        reorderList(input); // expecting 1,2
        ListNode.validateList(input, 1,2);
    }
    @Test
    void test3() {
        var input = ListNode.createList(1,2,3);
        reorderList(input); // expecting 1,3,2
        ListNode.validateList(input, 1,3,2);
    }
    @Test
    void test4() {
        var input = ListNode.createList(1,2,3,4);
        reorderList(input); // expecting 1,4,2,3
        ListNode.validateList(input, 1,4,2,3);
    }
    @Test
    void test5() {
        var input = ListNode.createList(1,2,3,4,5);
        reorderList(input); // expecting 1,5,2,4,3
        ListNode.validateList(input, 1,5,2,4,3);
    }
}
