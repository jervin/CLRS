package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinimumDeviation {
    public int minimumDeviation(int[] nums) {
        var set = new TreeSet<Integer>();
        for (int num : nums)
            set.add(num);
        var dev = set.last() - set.first();
        while ((set.first() %2 == 1 || set.last() %2 == 0) && dev > 1 && set.size() > 1) {
            // Need to handle 4 cases...
            // 1. When doubling the first element only leads to the smallest dev
            var dev1 = Integer.MAX_VALUE;
            if (set.first()%2 == 1) {
                var first = set.pollFirst();
                dev1 = Math.max(set.last(), first*2) - set.first();
                set.add(first);
            }
            // 2. When halving the last element only leads to the smallest dev
            var dev2 = Integer.MAX_VALUE;
            if (set.last()%2 == 0) {
                var last = set.pollLast();
                dev2 = set.last() - Math.min(set.first(), last/2);
                set.add(last);
            }
            // 3. When doing both gives you the smallest dev
            var dev3 = Integer.MAX_VALUE;
            if (set.first() %2 ==1 && set.last() %2 == 0) {
                var first = set.pollFirst();
                var last = set.pollLast();

                var newSet = new TreeSet<Integer>();
                newSet.addAll(set);
                newSet.add(first*2);
                newSet.add(last/2);
                dev3 = newSet.last() - newSet.first();

                set.add(first);
                set.add(last);
            }
            // 4. None of the operations help so terminate.
            if (dev <= Math.min(dev1, Math.min(dev2, dev3)))
                break;
            if (dev1 < dev2 && dev1 < dev3) {
                // Case 1 applies, only doubling the first element helps.
                var first = set.pollFirst();
                set.add(first * 2);
            } else if (dev2 < dev3) {
                // Case 2 applies, halving the last element reduces the deviation
                var last = set.pollLast();
                set.add(last/2);
            } else {
                // Case 3 applies need to add both a doubled first element and a halved second.
                var first = set.pollFirst();
                var last = set.pollLast();
                set.add(first*2);
                set.add(last/2);
            }
            dev = set.last() - set.first();
        }

        return dev;
    }

    @Test
    void test1() {
        assertEquals(3, minimumDeviation(new int[]{4,1,5,20,3}));
    }
    @Test
    void test2() {
        assertEquals(1, minimumDeviation(new int[] {3,5}));
    }

    @Test
    void test3() {
        assertEquals(2, minimumDeviation(new int[] {10,4,3}));
    }
    @Test
    void test4() {
        assertEquals(0, minimumDeviation(new int[]{8,1,2,1}));
    }
}
