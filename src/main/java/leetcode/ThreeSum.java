package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        if (nums == null || nums.length <= 2)
            return new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    final List<Integer> triple = Arrays.asList(nums[i], nums[j++], nums[k--]);
                    result.add(triple);
                }
                else if (sum > 0)
                    k--;
                else if (sum < 0)
                    j++;
            }
        }
        return new ArrayList<>(result);
    }

    @Test
    void test1() {
        val input = new int[] {-1,0,1,2,-1,-4};
        val expected = Arrays.asList( Arrays.asList(-1,-1,2), Arrays.asList(-1,0,1));
        val actual = threeSum(input);
        assertEquals(expected, actual);
    }
}
