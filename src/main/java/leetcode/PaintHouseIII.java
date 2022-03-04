package leetcode;

import org.checkerframework.checker.units.qual.min;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaintHouseIII {

    int M;
    int N;
    int TARGET;
    int[] houses;
    int[][] cost;

    int dp(int i, int j, int k) {
        if (k > TARGET || j > N || i >= M)
            return Integer.MAX_VALUE;
        // Check if we are in the last row or not.
        if (i == M-1) {
            if (houses[i] != 0)
                return k == TARGET && houses[i] == j ? 0 : Integer.MAX_VALUE;
            if (k == TARGET)
                return cost[M-1][j-1];
            if (k == TARGET - 1) {
                var answer = Integer.MAX_VALUE;
                for (int j0 = 1; j0 <= N; j0++) {
                    if (j0 == j)
                        continue;
                    answer = Math.min(answer, cost[M-1][j0-1]);
                }
                return answer;
            }
            return Integer.MAX_VALUE; // Anything else is invalid
        }
        var answer = Integer.MAX_VALUE;
        for (int j0 = 1; j0 <= N; j0++) {
            var value= j0 != j ? dp(i+1, j0, k + 1) : dp(i+1,j, k);
            answer = Math.min(answer, value);
        }
        if (answer == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return answer + houses[i] == 0 ? cost[i][j-1] : 0;
    }


    /**
     * int minCost(houses, cost, m, n, target)
     *  m - # of houses
     *  n - # of colors
     *  target - # of desired neighborhoods
     */
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        // Taking the top down approach first.
        this.M = m;
        this.N = n;
        this.TARGET = target;
        this.houses = houses;
        this.cost = cost;
        var answer = Integer.MAX_VALUE;
        if (houses[0] != 0) {
            answer = dp(0, houses[0], 1);
        } else {
            for (int j = 1; j <= n; j++)
                answer = Math.min(answer, dp(0, j, 1));
        }
        return answer != Integer.MAX_VALUE ? answer : -1;
    }
    public int minCostBottomUp(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] dp = new int[m][n+1][target+1];
        for (int[][] x : dp)
            for (int[] y : x)
                Arrays.fill(y, -1);
        // Base Case: Last house first
        int[][] lastHouse = dp[m-1];
        if (houses[m-1] == 0) {
            // k is fixed since the only way to be valid is if the number of neighborhoods is target.
            for (int color = 1; color <= n; color++) {
                lastHouse[color][target] = cost[m-1][color - 1];
            }
        } else {
            // Means last house is painted.  k is also fixed in this instance.
            lastHouse[houses[m-1]][target] = 0;
        }
        // Recurrence... iterate back to the first house.
        for (int h = m - 2; h >= 0; h--) {
            // Check if the current house is pained or not.
            int[][] prev = dp[h+1];
            int[][] currentDP = dp[h];
            if (houses[h] != 0) { // Painted already
                int currentHouseColor = houses[h];
                // Looking at the previous house values, all the colors not matching the currentHouseColor the # of neighborhoods is decremented.
                //  The color that matches the # of neighborhoods stays the same.
                for (int color = 1; color <= n; color++) {
                    for (int neighborhoods = 1; neighborhoods <= target; neighborhoods++) {
                        if (prev[color][neighborhoods] == -1)
                            continue;
                        var currentNeighborhoods = currentDP[color];
                        System.arraycopy(prev[color], 0, currentNeighborhoods, 0, target + 1);
                        if (color == currentHouseColor) {
                            continue;
                        }
                        // Now we need to decrement the number of neighborhoods and check to see if we are still valid.
                        for (int i = 0; i <= target; i++) {
                            if (currentNeighborhoods[i] == -1)
                                continue;
                            if (currentNeighborhoods[i] == 1) {
                                currentNeighborhoods[i] = -1; // Invalid now
                                continue;
                            }
                            currentNeighborhoods[i]--;
                        }
                    }
                }
                continue;
            }
            // Not painted yet...

        }
        // Answer is the minimum valid value in the matrix dp[0] where the number of target neigborhoods is 1.
        var answer = -1;
        for (int color = 1; color <= n; color++) {
            var min = dp[0][color][1];
            if (min == -1)
                continue;
            answer = Math.min(answer, min);
        }
        return answer;
    }

    @Test
    void test1() {
        var houses = new int[5];
        var cost = new int[][] {{1,10}, {10,1}, {10,1}, {1,10}, {5,1}};
        var target = 3;
        assertEquals(9, minCost(houses, cost, 5, 2, target));
    }

    @Test
    void test2() {
        var houses = new int[]{0,2,1,2,0};
        var cost = new int[][] {{1,10}, {10,1}, {10,1}, {1,10}, {5,1}};
        var target = 3;
        assertEquals(11, minCost(houses, cost, 5, 2, target));
    }

    @Test
    void test3() {
        var houses = new int[] {3,1,2,3};
        var cost = new int[][] {{1,10,1}, {10,1,1}, {10,1,1}, {1,10,1}};
        var target = 3;
        assertEquals(-1, minCost(houses, cost, 4, 3, target));
    }

    @Test
    void test4() {
        var houses = new int[1];
        var cost = new int[][] {{1,2,3,4,5}};
        var target = 1;
        assertEquals(1, minCost(houses, cost, 1, 5, target));
    }

    @Test
    void test5() {
        var houses = new int[2];
        var cost = new int[][] {{1,2,3}, {8,6,6}};
        var target = 1;
        assertEquals(8, minCost(houses, cost, 2, 3, target));
    }

    @Test
    void test6() {
        var houses = new int[] {0,1};
        var cost = new int[][] {{69,2,3}, {1,6,6}};
        var target = 1;
        assertEquals(69, minCost(houses, cost, 2, 3, target));
    }

    @Test
    void test7() {
        var houses = new int[] {2,1};
        var cost = new int[][] {{69,2,3}, {1,6,6}};
        var target = 1;
        assertEquals(-1, minCost(houses, cost, 2, 3, target));
    }

    @Test
    void test8() {
        var houses = new int[] {1,1};
        var cost = new int[][] {{69,2,3}, {1,6,6}};
        var target = 1;
        assertEquals(0, minCost(houses, cost, 2, 3, target));
    }
}
