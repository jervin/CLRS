package leetcode;

import org.checkerframework.checker.units.qual.min;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LargestRectangleInHistogram {
    int[] heights;
    int[] dp;
    int[][] minMatrix;

    int min(int start, int stop) {
        if (minMatrix[start][stop] != -1)
            return minMatrix[start][stop];
        if (minMatrix[start][stop-1] != -1) {
            minMatrix[start][stop] = Math.min(minMatrix[start][stop-1], heights[stop]);
            return minMatrix[start][stop];
        }
        int answer = Integer.MAX_VALUE;
        for (int i = start; i <= stop; i++) {
            answer = Math.min(answer, heights[i]);
        }
        minMatrix[start][stop] = answer;
        return answer;
    }
    int dp(int idx) {
        if (idx == 0)
            return heights[idx];
        if (dp[idx] > -1)
            return dp[idx];
        int answer = 0;
        answer = Math.max(answer, heights[idx]);
        answer = Math.max(answer,dp[idx-1] == -1 ? dp(idx - 1) : dp[idx-1]);
        int min = heights[idx];
        int width = 2;
        for (int i = idx - 1; i >= 0; i--) {
            min = Math.min(min, minMatrix[i][idx] == -1 ? min(i,idx) : minMatrix[i][idx]);
            answer = Math.max(answer, min * width);
            width++;
        }
        dp[idx] = answer;
        return answer;
    }
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 1)
            return heights[0];
        this.heights = heights;
        this.dp = new int[heights.length];
        Arrays.fill(dp, -1);
        this.minMatrix = new int[heights.length][heights.length];
        Arrays.stream(minMatrix).forEach( r -> Arrays.fill(r, -1));
        var answer = 0;
        for (int i = 0; i < heights.length; i++) {
            answer = Math.max(answer, dp(i));
        }
        return answer;
    }

    @Test
    void test1() {
        assertEquals(10, largestRectangleArea(new int[] {2,1,5,6}));
    }
    @Test
    void test2() {
        assertEquals(9, largestRectangleArea(new int[] {9,0}));
    }
    @Test
    void test3() {
        var input = new int[30000];
        Arrays.fill(input, 1);
        assertEquals(input.length, largestRectangleArea(input));
    }
}
