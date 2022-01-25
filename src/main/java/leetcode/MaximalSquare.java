package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximalSquare {
    int m;
    int n;

    boolean checkRow(char[][] matrix, int row, int colStart, int colEnd) {
        if (row >= m || colEnd >= n)
            return false;
        for (int i = colStart; i <= colEnd; i++) {
            if (matrix[row][i] == '0')
                return false;
        }
        return true;
    }
    boolean checkColumn(char[][] matrix, int rowStart, int rowEnd, int column) {
        if (rowEnd >= m || column >= n)
            return false;
        for (int i = rowStart; i <= rowEnd; i++) {
            if (matrix[i][column] == '0')
                return false;
        }
        return true;
    }

    int findArea(char[][] matrix, int i, int j) {
        int offset = 1;
        while(checkRow(matrix, i + offset, j, j + offset) && checkColumn(matrix, i, i + offset, j + offset)) {
            offset++;
        }
        return offset * offset;
    }
    public int maximalSquare(char[][] matrix) {
        int area = 0;
        m = matrix.length;
        n = matrix[0].length;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == '1')
                    area = Math.max(area, findArea(matrix, row, col));
            }
        }
        return area;
    }

    public int maximalSquareWithDP(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int currSqDim = 0, maxSqDim = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++){
                currSqDim = findSquare(matrix, i, j, dp);
                maxSqDim = Math.max(currSqDim, maxSqDim);
            }
        return maxSqDim * maxSqDim;
    }
    private int findSquare(char[][] matrix, int i, int j, int[][] dp){
        if (i >= matrix.length || j >= matrix[0].length || matrix[i][j] == '0')
            return 0;
        if (dp[i][j]!= 0)
            return dp[i][j];
        int sum = 1+ Math.min(Math.min(findSquare(matrix, i+1, j, dp),
                        findSquare(matrix, i, j+1, dp)),
                findSquare(matrix, i+1, j+1, dp));
        dp[i][j] = sum;
        return dp[i][j];
    }

    public int maximalSquareWithDPTake2(char[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] dp = new int[M+1][N+1];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == '0')
                    continue;
                dp[i+1][j+1] = 1;
            }
        }
        var dim = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == '0')
                    continue;
                dp[i+1][j+1] = Math.min(dp[i][j], Math.min(dp[i][j+1], dp[i+1][j])) + 1;
                dim = Math.max(dim, dp[i+1][j+1]);
            }
        }
        return dim * dim;
    }

    @Test
    void test0() {
        var input = new char[][] {{'1'}};
        assertEquals(1, maximalSquare(input));
        assertEquals(1, maximalSquareWithDPTake2(input));

        input = new char[][] {{'1', '1'}, {'1', '1'}};
        assertEquals(4, maximalSquare(input));
        assertEquals(4, maximalSquareWithDPTake2(input));

        input = new char[][] {{'0','0','0'}, {'0', '1', '1'}, {'0', '1', '1'}};
        assertEquals(4, maximalSquare(input));
        assertEquals(4, maximalSquareWithDPTake2(input));

        input = new char[][] {{'1','1','1'}, {'0', '1', '1'}, {'0', '1', '1'}};
        assertEquals(4, maximalSquare(input));
        assertEquals(4, maximalSquareWithDPTake2(input));

        input = new char[][]
                {{'0','1', '1'},
                {'0', '1', '1'},
                {'0', '1', '1'}};
        assertEquals(4, maximalSquare(input));
        assertEquals(4, maximalSquareWithDPTake2(input));

        input = new char[][]
                {{'1','1', '1'},
                {'1', '1', '1'},
                {'0', '0', '0'}};
        assertEquals(4, maximalSquare(input));
        assertEquals(4, maximalSquareWithDPTake2(input));
    }

    @Test
    void test1() {
        var input = new char[][] {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        assertEquals(4, maximalSquare(input));
        assertEquals(4, maximalSquareWithDPTake2(input));
    }

    @Test
    void test2() {
        var input = new char[][] {
                {'1','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','1','1','0'},
                {'1','1','1','1','1','1','1','0'},
                {'1','1','1','1','1','0','0','0'},
                {'0','1','1','1','1','0','0','0'}
        };
        assertEquals(16, maximalSquare(input));
        assertEquals(16, maximalSquareWithDPTake2(input));
    }
}
