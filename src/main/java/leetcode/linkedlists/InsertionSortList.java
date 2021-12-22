package leetcode.linkedlists;

import org.junit.jupiter.api.Test;

public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        ListNode[] array = new ListNode[5000];
        ListNode cursor = head;
        int i = 0;
        while (cursor != null) {
            array[i++] = cursor;
            cursor = cursor.next;
        }
        int N = i;
        for (int j = 1; j < N; j++) {
            ListNode key = array[j];
            i = j - 1;
            while (i >= 0 && array[i].val > key.val) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = key;
        }
        // Now lets fix the pointers
        cursor = array[0];
        for (i = 1; i < N; i++) {
            cursor.next = array[i];
            cursor = cursor.next;
        }
        array[N-1].next = null;
        return array[0];
    }

    @Test
    void test1() {
        var input = ListNode.createList(4,2,1,3);
        insertionSortList(input);
    }
}
