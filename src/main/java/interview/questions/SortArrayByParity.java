package interview.questions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * https://leetcode.com/problems/sort-array-by-parity/
 * Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
 * Return any array that satisfies this condition.
 *
 * Example 1:
 *
 * Input: nums = [3,1,2,4]
 * Output: [2,4,3,1]
 * Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 *
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [0]
 * Constraints:
 *     1 <= nums.length <= 5000
 *     0 <= nums[i] <= 5000
 */
public class SortArrayByParity {

    public int[] sortArrayByParity(int[] nums) {
        var idx = 0;
        while (idx < nums.length) {
            if (nums[idx] %2 == 0) {
                idx++;
                continue;
            }
            break;
        }
        if (idx == nums.length - 1)
            return nums;
        for (int i = idx; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                var tmp = nums[idx];
                nums[idx] = nums[i];
                nums[i] = tmp;
                idx++;
            }
        }
        return nums;
    }


    @Test
    void test1() {
        var input = new int[] {3,1,2,4};
        assertArrayEquals(new int[] {2,4,3,1}, sortArrayByParity(input));
    }
}
