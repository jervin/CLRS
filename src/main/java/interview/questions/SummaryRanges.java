package interview.questions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *  https://leetcode.com/problems/summary-ranges/
 *  228. Summary Ranges
 * You are given a sorted unique integer array nums.
 *
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 *
 * Each range [a,b] in the list should be output as:
 *
 * "a->b" if a != b
 * "a" if a == b
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * Example 2:
 *
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 20
 * -231 <= nums[i] <= 231 - 1
 * All the values of nums are unique.
 * nums is sorted in ascending order.
 */

public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {
        var answer = new ArrayList<String>();
        if (nums == null || nums.length == 0)
            return answer;
        int[] interval = new int[] {nums[0], nums[0]};
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num == interval[1] + 1) {
                interval[1] = num;
                continue;
            }
            // There is a gap here.
            if (interval[0] == interval[1]) {
                answer.add("" + interval[0]);
            } else {
                answer.add(interval[0] + "->" + interval[1]);
            }
            interval[0] = num;
            interval[1] = num;
        }
        if (interval[0] == interval[1]) {
            answer.add("" + interval[0]);
        } else {
            answer.add(interval[0] + "->" + interval[1]);
        }
        return answer;
    }

    @Test
    void test1() {
        var expected = Arrays.asList("0->2", "4->5", "7");
    }
}
