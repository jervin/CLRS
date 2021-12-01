package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  https://leetcode.com/problems/first-missing-positive/
 *  Given an unsorted integer array nums, return the smallest missing positive integer.
 *
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 105
 * -2^31 <= nums[i] <= 2^31 - 1
 */

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int minPositive = Integer.MAX_VALUE;
        int maxPositive = Integer.MIN_VALUE;
        int negCount = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num <= 0) {
                negCount++;
                continue;
            }
            set.add(num);
            minPositive = Math.min(minPositive, num);
            maxPositive = Math.max(maxPositive, num);
        }
        if (minPositive > 1)
            return 1;
        int numPositive = nums.length - negCount;

        if (set.size() == maxPositive) {
            return maxPositive + 1;
        }

        for (int i=minPositive; i < maxPositive; i++) {
            if (!set.contains(i))
                return i;
        }
        return -1; // Should never get here.
    }

    @Test
    void test1() {
        val input = new int[] {3,4,-1,1};
        assertEquals(2, firstMissingPositive(input));
    }
}
