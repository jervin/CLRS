package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaximizeDistanceToClosestPerson {
    public int maxDistToClosest(int[] seats) {
        var start = -1;
        var end = -1;
        var cursor = -1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                cursor = -1;
                continue;
            }
            if (cursor == -1) {
                cursor = i;
                if (start == -1) {
                    start = i;
                    end = i;
                }
                continue;
            }
            int lengthToBeat = start == 0 || end == seats.length - 1 ? 2 * (end - start + 1) : end - start + 1;
            int currentLength = cursor == 0 || i == seats.length - 1 ? 2 * (i - cursor + 1) : i - cursor + 1;
            if (currentLength > lengthToBeat) {
                start = cursor;
                end = i;
            }
        }
        if (start == -1)
            return 0;
        if (start == end)
            return 1;
        var delta = end - start + 1;
        if (start == 0 || end == seats.length - 1)
            return delta;
        if (delta % 2 == 1)
            return (delta + 1)/2;
        return delta / 2;
    }

    @Test
    void test1() {
        Assertions.assertEquals(5, maxDistToClosest(new int[]{1,0,0,1,0,0,0,1,1,1,0,0,0,0,0,0,1,0,0,0,0,0}));
    }
    @Test
    void test2() {
        Assertions.assertEquals(5, maxDistToClosest(new int[]{1,0,0,0,0,0,0,1,0,0,0,0,0}));
    }
}
