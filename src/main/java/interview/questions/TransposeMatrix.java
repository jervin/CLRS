package interview.questions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 867. Transpose Matrix
 *
 * Given a 2D integer array matrix, return the transpose of matrix.
 *
 * The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.
 *
 * Example 1:
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[1,4,7],[2,5,8],[3,6,9]]
 * Example 2:
 *
 * Input: matrix = [[1,2,3],[4,5,6]]
 * Output: [[1,4],[2,5],[3,6]]
 */

public class TransposeMatrix {

    public int[][] transpose(int[][] matrix) {
        var M = matrix.length;
        var N = matrix[0].length;

        var answer = new int[N][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                answer[j][i] = matrix[i][j];
            }
        }
        return answer;
    }

    @Test
    void test1() {

    }
}
