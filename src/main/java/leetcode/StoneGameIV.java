package leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoneGameIV {
    List<Integer> perfectSquares(int n) {
        List<Integer> squares = new ArrayList<Integer>();
        int i = 1;
        while (i*i <= n) {
            squares.add(i*i);
            i++;
        }
        return squares;
    }
    public boolean winnerSquareGame(int n) {
        if (n == 1 || n == 3 || n == 4 || n == 6)
            return true;
        if (n == 2 || n == 5)
            return false;
        List<Integer> squares = perfectSquares(n);
        if (squares.get(squares.size() - 1) == n)
            return true;
        for (int square : squares) {
            if (!winnerSquareGame( n - square))
                return true;
        }
        return false;
    }

    public boolean winnerSquareGameDP(int n) {
        boolean[] dp = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j*j <= i; j++) {
                if (!dp[i-j*j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    @Test
    void test1() {
        assertFalse(winnerSquareGame(7));
        assertFalse(winnerSquareGameDP(7));
    }
}
