package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Leetcode: (322) https://leetcode.com/problems/coin-change/
 */

public class CoinChange {
    int[] coins;
    int amount;
    int[] dp;

    int dfs(int amount) {
        if (amount < 0)
            return -1;
        if (amount == 0)
            return 0;
        if (dp[amount] != 0)
            return dp[amount];
        int answer = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = dfs(amount - coin);
            if (result >= 0 && result < answer)
                answer = result + 1;
        }
        dp[amount] = answer != Integer.MAX_VALUE ? answer: -1;
        return dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        this.coins = coins;
        Arrays.sort(this.coins);
        this.amount = amount;
        dp = new int[amount + 1];
        return dfs(amount);
    }

    @Test
    void test1() {
        assertEquals(20, coinChange(new int[]{186,419,83,408}, 6249));
    }
}
