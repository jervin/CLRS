package leetcode;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestTurbulentSubarray {
    public int maxTurbulenceSize(int[] array) {
        if (array.length == 1)
            return 1;
        int maxLength = 0;
        Boolean flag = null;
        int cursor = 0;
        for (int k = 0; k < array.length; k++) {
            if (k == array.length - 1) {
                maxLength = Math.max(maxLength, flag != null ? k - cursor + 1 : k - cursor);
                break;
            }
            if (flag == null || array[k] == array[k+1]) {
                if (array[k] == array[k+1]) {
                    maxLength = Math.max(maxLength, k - cursor);
                    cursor = k;
                    flag = null;
                    continue;
                }
                boolean odd = k % 2 == 1;
                if (array[k] > array[k+1]) {
                    flag = odd;
                } else {
                    flag = !odd;
                }
            }
            if (k % 2 == 0) {
                // Even indice
                if (flag != null && flag.booleanValue()) {
                    // Top Condition
                    if (array[k] >= array[k+1]) {
                        maxLength = Math.max(maxLength, k - cursor);
                        cursor = k;
                        flag = null;
                        continue;
                    } else {
                        maxLength = Math.max(maxLength, k - cursor);
                    }
                } else {
                    // Bottom Condition
                    if (array[k] <= array[k+1]) {
                        maxLength = Math.max(maxLength, k - cursor);
                        cursor = k;
                        flag = null;
                        continue;
                    } else {
                        maxLength = Math.max(maxLength, k - cursor);
                    }
                }
            } else {
                // Odd indice
                if (flag != null && flag.booleanValue()) {
                    // Top condition
                    if (array[k] <= array[k+1]) {
                        maxLength = Math.max(maxLength, k - cursor);
                        cursor = k;
                        flag = null;
                        continue;
                    } else {
                        maxLength = Math.max(maxLength, k - cursor);
                    }
                } else {
                    // Bottom Condition
                    if (array[k] >= array[k+1]) {
                        maxLength = Math.max(maxLength, k - cursor);
                        cursor = k;
                        flag = null;
                        continue;
                    }
                    else {
                        maxLength = Math.max(maxLength, k - cursor);
                    }
                }
            }
        }
        return maxLength;
    }

    public int maxTurbulenceSizeCorrect(int[] A) {
        int N = A.length;
        int ans = 1;
        int anchor = 0;

        for (int i = 1; i < N; ++i) {
            int c = Integer.compare(A[i-1], A[i]);
            if (c == 0) {
                anchor = i;
            } else if (i == N-1 || c * Integer.compare(A[i], A[i+1]) != -1) {
                ans = Math.max(ans, i - anchor + 1);
                anchor = i;
            }
        }

        return ans;
    }

    @Test
    void test1() {
        val solution = new LongestTurbulentSubarray();
        assertEquals(1, solution.maxTurbulenceSize(new int[] { 0 }));
        assertEquals(1, solution.maxTurbulenceSize(new int[] { 1 }));
        assertEquals(1, solution.maxTurbulenceSize(new int[] { 0, 0 } ));
        assertEquals(2, solution.maxTurbulenceSize(new int[] { 0, 1 } ));
        assertEquals(1, solution.maxTurbulenceSize(new int[] { 0, 0, 0 }));
        assertEquals(2, solution.maxTurbulenceSize(new int[] { 0, 0, 1 }));
        assertEquals(3, solution.maxTurbulenceSize(new int[] { 0, 1, 0 }));
        assertEquals(2, solution.maxTurbulenceSize(new int[] { 0, 1, 1 }));
        assertEquals(2, solution.maxTurbulenceSize(new int[] { 1, 0, 0 }));
        assertEquals(3, solution.maxTurbulenceSize(new int[] { 1, 0, 1 }));
        assertEquals(2, solution.maxTurbulenceSize(new int[] { 1, 1, 0 }));
        assertEquals(1, solution.maxTurbulenceSize(new int[] { 1, 1, 1 }));
        assertEquals(5, solution.maxTurbulenceSize(new int[] { 0, 1, 1, 0, 1, 0, 1, 1, 0, 0 } ));
        assertEquals(5, solution.maxTurbulenceSize(new int[] { 9, 4, 2, 10, 7, 8, 8, 1, 9 } ));
    }
}
