package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChampagneTower {

    int poured;
    double[][] memo;

    double dp(int row, int col) {
        if (row < 0 || col < 0)
            return 0.0d;
        if (row == 0) {
            if (col != 0)
                return 0.0d;
            // Base case
            return Math.max(0, poured - 1);
        }
        if (memo[row][col] != -1.0d) {
            return memo[row][col];
        }

        var answer = 0.0;
        if (col > 0) {
            answer += 0.5 * dp(row - 1, col -1);
        }
        answer += 0.5 * dp(row - 1, col);
        answer = Math.max(0, answer - 1);
        memo[row][col] = answer;
        return answer;
    }

    public double champagneTower(int poured, int query_row, int query_glass) {
        if (query_row == 0 && query_glass == 0) {
            return poured - 1 >= 0 ? 1.0d : 0.0d;
        }
        this.poured = poured;
        memo = new double[query_row + 1][ query_row + 1];
        for (double[] row : memo)
            Arrays.fill(row, -1.0d);
        var overflow = 0.5* dp(query_row-1, query_glass-1) + 0.5*dp(query_row-1, query_glass);
        return Math.min(1.0d, overflow);
    }

    @Test
    void test1() {
        assertEquals(0.5d, champagneTower(2,1,1));
    }
}
