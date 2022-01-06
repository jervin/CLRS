package leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        if (intervals.length == 1)
            return intervals;
        var answer = new ArrayList<int[]>();
        int[] cursor = null;
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (cursor == null) {
                cursor = interval;
                answer.add(cursor);
                continue;
            }
            cursor[0] = Math.min(cursor[0], interval[0]);
            if (cursor[1] < interval[0]) {
                // We have two separate intervals
                answer.add(interval);
                cursor = interval;
                continue;
            }
            cursor[1] = Math.max(cursor[1],interval[1]);
        }
        return answer.stream().toArray(int[][]::new);
    }

    @Test
    void test1() {
        var input = new int[][] {{1,4},{0,4}};
        assertArrayEquals(new int[][] {{0,4}}, merge(input));
    }
    @Test
    void test2() {
        var input = new int[][] {{1,3},{2,6},{8,10},{15,18}};
        assertArrayEquals(new int[][] {{1,6},{8,10},{15,18}}, merge(input));
    }
    @Test
    void test3() {
        var input = new int[][] {{1,4},{0,1}};
        assertArrayEquals(new int[][] {{0,4}}, merge(input));
    }
}
