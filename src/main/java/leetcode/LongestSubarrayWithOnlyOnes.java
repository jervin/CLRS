package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LongestSubarrayWithOnlyOnes {
    public int longestSubarray(int[] nums) {
        var start = -1;
        var delIdx = -1;
        var answer = 0;
        var len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && start == -1 && delIdx == -1) {
                continue;
            }
            if (nums[i] == 0 && start >= 0) {
                if (delIdx < start) {
                    start = delIdx;
                    delIdx = i;
                    len = i+1 - start;
                }
                answer = Math.max(answer, len);
                continue;
            }
            if (nums[i] == 1) {
                if (start == -1)
                    start = i;
                len++;
                answer = Math.max(answer,len);
            }
        }
        return start >= 0 ? answer : answer - 1;
    }
    @Test
    void test1() {
        assertEquals(3, longestSubarray(new int[] {1,1,0,1}));
        assertEquals(5, longestSubarray(new int[] {0,1,1,1,0,1,1,0,1}));
        assertEquals(2, longestSubarray(new int[] {1,1,1}));
    }
}
