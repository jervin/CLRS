package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubArraySum {
    public int subarraySum(int[] nums, int k) {
        int N = nums.length;
        int[] sums = new int[N];
        var sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
            sums[i] = sum;
        }
        var answer = nums[0] == k ? 1 : 0;
        for (int i = 1; i < N; i++) {
            if (sums[i] == k)
                answer++;
            for (int j = 0; j < i; j++) {
                if(sums[i] - sums[j] == k)
                    answer++;
            }
        }
        return answer;
    }
    @Test
    void test1() {
        assertEquals(2, subarraySum(new int[]{1,2,3}, 3));
    }
}
