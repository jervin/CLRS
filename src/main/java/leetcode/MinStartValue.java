package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/minimum-value-to-get-positive-step-by-step-sum/submissions/
 *
 */

public class MinStartValue {
    public int minStartValue(int[] nums) {
        int maxDeficit = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum < 0) {
                maxDeficit = Math.min(maxDeficit, sum);
            }
        }
        return (-1*maxDeficit) + 1;
    }
    @Test
    void test1() {
        val input = new int[] {1,-2,-3};
        assertEquals(5, minStartValue(input));
    }
}
