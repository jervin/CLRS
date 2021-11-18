package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Leetcode: 448. Find All Numbers Disappeared in an Array
 *
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 * Example 2:
 *
 * Input: nums = [1,1]
 * Output: [2]
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 *
 *
 * Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 */
public class FindMissingNumbers {
    public List<Integer> findDisappearedNumbersOld(int[] nums) {
        boolean[] notMissing = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            notMissing[nums[i] - 1] = true;
        }
        List<Integer> disappeared = new ArrayList<>();
        for (int i = 0; i < notMissing.length; i++) {
            if (!notMissing[i])
                disappeared.add(i + 1);
        }
        return disappeared;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i  = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0)
                nums[index] = nums[index] * -1;
        }
        var answer = new ArrayList<Integer>();
        for (int i  = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                answer.add(i + 1);
        }
        return answer;
    }

    @Test
    void test1() {
        val nums = new int[] {4,3,2,7,8,2,3,1};
        val expected = Arrays.asList(5,6);
        assertEquals(expected, findDisappearedNumbers(nums));
    }
}
