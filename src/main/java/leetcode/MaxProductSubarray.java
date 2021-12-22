package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
 *
 * It is guaranteed that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

public class MaxProductSubarray {
    int[] findMaxCrossingSubarray(int[] nums, int low, int high, int mid) {
        int leftProduct = 1;
        for (int i = mid; i >= low; i--)
            leftProduct = leftProduct * nums[i];

        int rightProduct = 1;
        for (int i = mid + 1; i <= high; i++)
            rightProduct = rightProduct * nums[i];

        return new int[] {rightProduct * leftProduct, low, high};
    }
    public int[] findMaxSubArray(int[] nums, int low, int high) {
        if (high == low)
            return new int[] {nums[low], low, high};
        int mid = low + ((high - low)/2);
        int[] left = findMaxSubArray(nums, low, mid);
        int[] right = findMaxSubArray(nums, mid + 1, high);
        int[] crossing = findMaxCrossingSubarray(nums, low, high, mid);
        if (left[0] > right[0] && left[0] > crossing[0])
            return left;
        if (right[0] > left[0] && right[0] > crossing[0])
            return right;
        return crossing;
    }
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int answer = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(min * nums[i], max * nums[i]), nums[i]);
            min = Math.min(Math.min(min * nums[i], temp * nums[i]), nums[i]);
            answer = Math.max(answer, max);
        }
        return answer;
    }

    @Test
    void test1() {
        assertEquals(6, maxProduct(new int[] {2,3,-2,4}));
        assertEquals(0, maxProduct(new int[] {-2,0,-1}));
        assertEquals(-2, maxProduct(new int[] {-2}));
        assertEquals(2, maxProduct(new int[] {0, 2}));
        assertEquals(24, maxProduct(new int[] {-2,3,-4}));
        assertEquals(24, maxProduct(new int[] {2,-5,-2,-4,3}));
        assertEquals(3, maxProduct(new int[] {-2,3,0,-4}));
        assertEquals(24, maxProduct(new int[] {2,-5,-2,-4,3,0,2,3,-2,4}));
    }
}
