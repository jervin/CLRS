package leetcode;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/770/
 */

public class RotateImage {
    public void rotateRing(int[][] matrix, int startRow, int endRow, int startCol, int endCol) {
        for (int i = startRow; i < endRow; i++) {
            int offset = i - startRow;
            int value0 = matrix[startRow + offset][endCol];
            matrix[startRow + offset][endCol] = matrix[startRow][startCol + offset];

            int value1 = matrix[endRow][endCol - offset];
            matrix[endRow][endCol - offset] = value0;

            int value2 = matrix[endRow - offset][startCol];
            matrix[endRow - offset][startCol] = value1;

            matrix[startRow][startCol + offset] = value2;
        }
    }
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length <= 1)
            return;
        int n = matrix.length;
        int startRow = 0;
        int endRow = n - 1;
        int startCol = 0;
        int endCol = n - 1;
        while (startRow < endRow ) {
            rotateRing(matrix, startRow, endRow, startCol, endCol);
            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
    }


    @Test
    void test1() {
        val input = new int[][] { {1,2}, {3,4} };
        rotate(input);
        assertEquals(3, input[0][0]);
        assertEquals(1, input[0][1]);
        assertEquals(4, input[1][0]);
        assertEquals(2, input[1][1]);
    }

    @Test
    void test2() {
        val input = new int[][] { {1,2,3}, {4,5,6}, {7,8,9} };
        rotate(input);
        assertEquals(7, input[0][0]);
        assertEquals(4, input[0][1]);
        assertEquals(1, input[0][2]);
        assertEquals(8, input[1][0]);
        assertEquals(5, input[1][1]);
        assertEquals(2, input[1][2]);
        assertEquals(9, input[2][0]);
        assertEquals(6, input[2][1]);
        assertEquals(3, input[2][2]);
    }

}
