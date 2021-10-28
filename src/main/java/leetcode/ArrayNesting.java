package leetcode;

// https://leetcode.com/explore/challenge/card/september-leetcoding-challenge-2021/636/week-1-september-1st-september-7th/3960/

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        int maxValue = 0;
        boolean[] set = new boolean[nums.length];
        for (int index = 0; index < nums.length; index++) {
            int sizeSet = 1;
            set[nums[index]] = true;
            int nextIndex = nums[index];
            while (!set[nums[nextIndex]]) {
                set[nums[nextIndex]] = true;
                nextIndex = nums[nextIndex];
                sizeSet++;
            }
            maxValue = Math.max(maxValue, sizeSet);
        }
        return maxValue;
    }

    @Test
    void testing() {
        val solution = new ArrayNesting();
        assertEquals(4, solution.arrayNesting(new int[] {5,4,0,3,1,6,2}));
        assertEquals(1, solution.arrayNesting(new int[] {0,1,2,3}));
    }
}
