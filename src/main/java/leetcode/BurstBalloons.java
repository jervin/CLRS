package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BurstBalloons {
    int N;
    int[][] memo;

    int left(int[] nums, int i) {
        if (i > 0)
            return nums[i-1];
        return 1;
    }
    int right(int[] nums, int i) {
        if (i < N - 1)
            return nums[i + 1];
        return 1;
    }
    int traverse(int[] nums, int start, int end) {
        if (start > end)
            return 0;
        if (memo[start][end] != 0)
            return memo[start][end];
        int max = 0;
        for (int i = start; i <= end; i++) {
            var value = left(nums, start) * nums[i] * right(nums, end);
            max = Math.max(max, traverse(nums, start, i-1) + value + traverse(nums, i+1, end));
        }
        memo[start][end] = max;
        return max;
    }

    public int maxCoins(int[] nums) {
        N = nums.length;
        memo = new int[N][N];
        return traverse(nums, 0, N-1);
    }

    @Test
    void test1() {
        assertEquals(167, maxCoins(new int[] {3,1,5,8}));
    }
}
