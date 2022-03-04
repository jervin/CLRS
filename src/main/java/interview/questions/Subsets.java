package interview.questions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/subsets/
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 */
public class Subsets {
    List<Integer> subset(int[] nums, int mask) {
        var subset = new ArrayList<Integer>();
        int offset = 0;
        while (mask > 0) {
            if (mask % 2 == 1)
                subset.add(nums[offset]);
            offset++;
            mask >>= 1;
        }
        return subset;
    }
    public List<List<Integer>> subsets(int[] nums) {
        int N = (int)Math.pow(2, nums.length);
        var answer = new ArrayList<List<Integer>>();
        for (int i = 0; i < N; i++) {
            answer.add(subset(nums, i));
        }
        return answer;
    }

    // Follow up with duplicates.
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int N = (int)Math.pow(2, nums.length);
        Arrays.sort(nums);
        var answer = new LinkedHashSet<List<Integer>>();
        for (int i = 0; i < N; i++) {
            answer.add(subset(nums, i));
        }
        return new ArrayList<>(answer);
    }

    @Test
    void test1() {
        var expected = asList(new ArrayList<>(),
                asList(1),
                asList(2),
                asList(1,2),
                asList(3),
                asList(1,3),
                asList(2,3),
                asList(1,2,3)
                );
        assertEquals(expected, subsets(new int[] {1,2,3}));
    }
}
