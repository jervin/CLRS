package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuSolver {
    boolean solved = false;
    boolean isSolved(char[][] board) {
        if (solved)
            return true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    return false;
            }
        }
        solved = true;
        return true;
    }

    boolean isSet(char[][] board, int row, int col) {
        return board[row][col] != '.';
    }
    boolean canSet(char[][] board, int row, int col, int intValue) {
        var charValue = (char)(intValue + '0');
        // 1. Check row
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == charValue)
                return false;
        }
        // 2. Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == charValue)
                return false;
        }
        // 3. Check 3x3 square
        int rowStart = (row / 3) * 3;
        int colStart = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[rowStart + i][colStart + j] == charValue)
                    return false;
            }
        }
        return true;
    }
    void dfs(char[][] board, int row, int col) {
        if (row >= 9 || col >= 9)
            return;
        if (isSolved(board))
            return;
        if (isSet(board, row, col)) {
            if (col < 8)
                dfs(board, row, col + 1);
            else
                dfs(board, row + 1, 0);
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (!canSet(board, row, col, i))
                continue;
            board[row][col] = (char) (i + '0');
            if (col < 8)
                dfs(board, row, col + 1);
            else
                dfs(board, row + 1, 0);
            if (!isSolved(board))
                board[row][col] = '.';
        }
    }

    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0);
    }

    @Test
    void test1() {
        var input = new char[][] {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(input);
        assertTrue(isSolved(input));
    }

    @Test
    void test2() {
        var input = new char[][] {{'.','.','9','7','4','8','.','.','.'},{'7','.','.','.','.','.','.','.','.'},{'.','2','.','1','.','9','.','.','.'},{'.','.','7','.','.','.','2','4','.'},{'.','6','4','.','1','.','5','9','.'},{'.','9','8','.','.','.','3','.','.'},{'.','.','.','8','.','3','.','2','.'},{'.','.','.','.','.','.','.','.','6'},{'.','.','.','2','7','5','9','.','.'}};
        solveSudoku(input);
        assertTrue(isSolved(input));
    }
}
