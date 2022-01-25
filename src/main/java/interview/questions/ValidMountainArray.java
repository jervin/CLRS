package interview.questions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/valid-mountain-array/
 * (941) Valid Mountain Array
 */
public class ValidMountainArray {
    public boolean validMountainArray(int[] arr) {
        if (arr.length == 1)
            return false;
        if (arr.length == 2)
            return false;
        int prev = arr[0];
        boolean increasing = true;
        for (int i = 1; i < arr.length; i++) {
            if (prev == arr[i])
                return false;
            if (increasing && prev > arr[i]) {
                if (i == 1)
                    return false;
                increasing = false;
                prev = arr[i];
                continue;
            }
            if (!increasing && prev < arr[i])
                return false;
            prev = arr[i];
        }
        if (increasing)
            return false;
        return true;
    }

    @Test
    void test1() {
        assertFalse(validMountainArray(new int[] {5}));
        assertFalse(validMountainArray(new int[] {5,4}));
        assertFalse(validMountainArray(new int[] {1,2,3}));
        assertFalse(validMountainArray(new int[] {3,2,1}));
        assertFalse(validMountainArray(new int[] {1,2,2}));
        assertFalse(validMountainArray(new int[] {2,2,3}));
        assertTrue(validMountainArray(new int[] {1,2,1}));
    }
}
