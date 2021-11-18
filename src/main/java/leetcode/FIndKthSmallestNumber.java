package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/
 * 668. Kth Smallest Number in Multiplication Table
 * Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is an integer matrix mat where mat[i][j] == i * j (1-indexed).
 *
 * Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.
 */
public class FIndKthSmallestNumber {

    public int findKthNumber(int m, int n, int k) {
        int[] result = new int[m*n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                result[row * n + col] = (row + 1) * (col + 1);
            }
        }
        Arrays.sort(result);
        return result[k - 1];
    }

    @Test
    void test1() {
        assertEquals(3, findKthNumber(3,3,5));
    }

    @Test
    void test2() {
        assertEquals(6, findKthNumber(2,3,6));
    }
}
