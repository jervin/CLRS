package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CountingAllValidPickupDeliveryOpts {

    static final int MODULO = 1000000007;
    long[][] memo;

    long dp(int i, int j) {
        if (memo[i][j] > -1)
            return memo[i][j];
        // Base cases
        if ((i==0 && j==0) || (i==1 && j==1)) {
            return 1;
        }
        if (i < 0 || j < 0 || i > j)
            return 0;
        //memo[i][j] = 0;
        long answer = i > 0 ? i * dp(i-1, j) : 0;
        if (i == j) {
            memo[i][j] = answer % MODULO;
        }
        else{
            memo[i][j] = (answer + (j-i)*dp(i, j-1)) % MODULO;
        }
        return memo[i][j];
    }

    public int countOrders(int n) {
        memo = new long[n+2][n+2];
        for (long[] row : memo)
            Arrays.fill(row, -1L);
        memo[0][0] = 1;
        memo[0][1] = 1;
        memo[1][1] = 1;
        return (int)dp(n, n);
    }

    public int countOrdersBottomUp(int n) {
        long[][] dp = new long[n+2][n+2];
        for (long[] row : dp)
            Arrays.fill(row, -1L);
        // Setting up base cases
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[0][i] = (i * dp[0][i-1]) % MODULO;
        }
        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i > j) // invalid state
                    continue;
                var answer = ((long)i*dp[i-1][j])% MODULO;
                if (i == j)
                    dp[i][j] = answer;
                else
                    dp[i][j] = (answer + (((long)(j-i)*dp[i][j-1])) % MODULO) % MODULO;
            }
        }
        return (int)dp[n][n];
    }

    @Test
    void test1() {
        assertEquals(1, countOrders(0));
        assertEquals(1, countOrders(1));
        assertEquals(6, countOrders(2));
        assertEquals(90, countOrders(3));
        assertEquals(448239899, countOrders(91));
    }

    @Test
    void test2() {
        assertEquals(1, countOrdersBottomUp(0));
        assertEquals(1, countOrdersBottomUp(1));
        assertEquals(6, countOrdersBottomUp(2));
        assertEquals(90, countOrdersBottomUp(3));
        assertEquals(448239899, countOrdersBottomUp(91));
    }
}
