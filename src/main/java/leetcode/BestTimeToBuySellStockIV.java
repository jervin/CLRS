package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BestTimeToBuySellStockIV {

    public int maxProfit(int k, int[] prices) {
        int[][][] dp = new int[prices.length + 1][k+1][2];
        for (int i = prices.length - 1; i >= 0; i--) {
            for (int j = k; j > 0; j--) {
                for (int h = 0; h <= 1; h++) {
                    // System.out.println("(i,j,h): (" + i + "," + j + "," + h +")");
                    int doNothing = dp[i+1][j][h];
                    int doSomething;
                    if (h == 0)
                        doSomething = -prices[i] + dp[i+1][j][1];
                    else
                        doSomething = prices[i] + dp[i+1][j-1][0];
                    dp[i][j][h] = Math.max(doNothing, doSomething);
                }
            }
        }
        return dp[0][k][0];
    }

    @Test
    void test1() {
        assertEquals(2, maxProfit(2, new int[] {2,4,1}));
    }
}
