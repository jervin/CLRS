package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxProfit {
    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/564/

    // Returning three ints, one is the left index of the max array, the second is the right index of
    //  the max array and third is the sum.
    public int[] maxCrossingSubarray(int[] array, int low, int mid, int high) {
        int left = mid;
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = mid; i>= low; i--) {
            sum += array[i];
            if (sum <= leftSum)
                continue;
            leftSum = sum;
            left = i;
        }
        int right = mid + 1;
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += array[i];
            if (sum <= rightSum)
                continue;
            rightSum = sum;
            right = i;
        }
        return new int[] { left, right, leftSum + rightSum };
    }

    // Returning three ints, one is the left index of the max array, the second is the right index of
    //  the max array and third is the sum.
    public int[] findMaximumSubarray(int[] array, int low, int high) {
        if (low == high)
            return new int[] { low, high, array[low] };
        int mid = (high + low) / 2;
        int[] leftSide = findMaximumSubarray(array, low, mid);
        int[] rightSide = findMaximumSubarray(array, mid + 1, high);
        int[] crossing = maxCrossingSubarray(array, low, mid, high);

        if (leftSide[2] >= rightSide[2] && leftSide[2] >= crossing[2])
            return leftSide;
        if (crossing[2] >= leftSide[2] && crossing[2] >= rightSide[2])
            return crossing;
        return rightSide;
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i-1];
            if (diff > 0)
                maxProfit += diff;
        }
        return maxProfit;
    }

    @Test
    void test1() {
        assertEquals(7, maxProfit(new int[] {7,1,5,3,6,4} ));
    }
}
