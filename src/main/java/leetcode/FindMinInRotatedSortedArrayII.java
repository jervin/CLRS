package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindMinInRotatedSortedArrayII {
    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

    public int findMinIndex(int[] nums, int start, int end) {
        // Base case of single element array
        if (start == end)
            return start;
        // Check for base case of two element array
        if (end - start == 1)
            return nums[start] <= nums[end] ? start : end;
        // First check the ends of the array...
        if (nums[end] > nums[start])
            return start;
        // Now compute the mid point
        int mid = (start + end)/2;
        // Check the mid point plus the next.
        if (nums[mid] > nums[mid+1])
            return mid + 1;
        // Check the first half
        if (nums[start] > nums[mid])
            return findMinIndex(nums, start, mid);
        if (nums[mid + 1] > nums[end])
            return findMinIndex(nums, mid + 1, end);
        // Degenerate case where we have so many duplicates that our comparisons fail
        int leftIndex = findMinIndex(nums, start, mid);
        int rightIndex = findMinIndex(nums, mid + 1, end);
        return nums[leftIndex] <= nums[rightIndex] ? leftIndex : rightIndex;
    }

    public int findMin(int[] nums) {
        return nums[findMinIndex(nums, 0, nums.length - 1)];
    }

    @Test
    void test1() {
        val input = new int[] {1,3,5};
        assertEquals(1, findMin(input));
    }

    @Test
    void test2() {
        val input = new int[] {2,2,2,0,1};
        assertEquals(0, findMin(input));
    }

    @Test
    void test3() {
        val input = new int[] {10,1,10,10,10};
        assertEquals(1, findMin(input));
    }
}
