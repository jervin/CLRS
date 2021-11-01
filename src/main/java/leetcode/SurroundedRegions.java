package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/surrounded-regions/
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 */
public class SurroundedRegions {
    // Always remember to mark where you have been somehow.
    public boolean traverse(int row, int col, char[][] board) {
        if (board[row][col] == 'X' || board[row][col] == 'o')
            return true;
        if (row <= 0 || row >= board.length - 1)
            return false;
        if (col <= 0 || col >= board[row].length - 1)
            return false;
        board[row][col] = 'o';
        boolean up = traverse(row - 1, col, board);
        boolean down = traverse(row + 1, col, board);
        boolean left = traverse(row, col - 1, board);
        boolean right = traverse(row, col + 1, board);
        if (!up || !down || !left || !right)
            return false;
        return true;
    }
    public void capture(int row, int col, char[][] board) {
        if (board[row][col] == 'X')
            return;
        if (row <= 0 || row >= board.length - 1)
            return;
        if (col <= 0 || col >= board[row].length - 1)
            return;
        board[row][col] = 'X';
        capture(row - 1, col, board);
        capture(row + 1, col, board);
        capture(row, col - 1, board);
        capture(row, col + 1, board);
    }
    public void reset(int row, int col, char[][] board) {
        if (board[row][col] == 'X' || board[row][col] == 'O')
            return;
        if (board[row][col] == 'o') {
            board[row][col] = 'O';
        } else {
            board[row][col] = 'X';
        }
        reset(row - 1, col, board);
        reset(row + 1, col, board);
        reset(row, col - 1, board);
        reset(row, col + 1, board);
    }
    public void solve(char[][] board) {
        // The trick is going to be to scan for 'O''s and then capture the region.
        //  Also detect if the region hits a border, if not flip to X.  Be nice to do this in
        //  place to avoid creating a coordinate class to capture visited regions.
        for (int i = 0; i < board.length; i++) {
            char[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 'X')
                    continue;
                boolean capture = traverse(i, j, board);
                if (capture)
                    capture(i, j, board);
                else
                    reset(i,j,board);
            }
        }
    }

    @Test
    void test1() {
        val input = new char[][] {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        solve(input);
    }

    @Test
    void test2() {
        val input = new char[][] {{'O','O','O'},{'O','O','O'},{'O','O','O'}};
        solve(input);
    }

}
