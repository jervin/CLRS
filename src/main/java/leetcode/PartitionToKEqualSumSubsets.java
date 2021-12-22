package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PartitionToKEqualSumSubsets {
    int[] input;
    boolean[] used;
    boolean findSubset(int[] nums, int idx, int target) {
        if (target == 0)
            return true;
        if (idx >= nums.length || input[idx] > target)
            return false;
        if (used[idx])
            return findSubset(nums, idx + 1, target);
        used[idx] = true;
        if (input[idx] == target)
            return true;
        target = target - input[idx];
        for (int j = idx + 1; j < input.length; j++) {
            if (findSubset(nums, j, target))
                return true;
        }
        used[idx] = false;
        return false;
    }
    public boolean canPartitionKSubsets(int[] nums, int k) {
        var sum = Arrays.stream(nums).sum();
        if (sum % k != 0)
            return false;
        var target = sum / k;
        Arrays.sort(nums);
        used = new boolean[nums.length];
        input = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            input[i] = nums[nums.length - 1 - i];
        }
        for (int i = 0; i < k; i++) {
            if (!findSubset(nums, 0, target))
                return false;
        }
        return true;
    }

    @Test
    void test1() {
        assertTrue(canPartitionKSubsets(new int[] {1,1,1,1,2,2,2,2}, 4));
    }

    @Test
    void test2() {
        assertTrue(canPartitionKSubsets(new int[] {4,16,5,3,10,4,4,4,10}, 3));
    }
}
