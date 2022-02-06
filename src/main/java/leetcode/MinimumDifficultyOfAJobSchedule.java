package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinimumDifficultyOfAJobSchedule {
    int N, D;
    int[][] memo;
    int[] jobs;
    int[] hardest;

    int dp(int idx, int d) {
        if (d == D)
            return hardest[idx];
        if (memo[idx][d] == -1) {
            int answer = Integer.MAX_VALUE;
            int hardest = 0;
            for (int i = idx; i < N - (D - d); i++) {
                hardest = Math.max(hardest, jobs[i]);
                // Recurrence relation
                answer = Math.min(answer, hardest + dp(i + 1, d + 1));
            }
            memo[idx][d] = answer;
        }
        return memo[idx][d];
    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        this.N = jobDifficulty.length;
        this.jobs = jobDifficulty;
        this.D = d;
        if (d > N)
            return -1;
        hardest = new int[N];
        int current = 0;
        for (int i = N-1; i >= 0; i--) {
            current = Math.max(current, jobs[i]);
            hardest[i] = current;
        }
        memo = new int[N][D+1]; // Added one to D to avoid off-by-one errors.
        for (int i = 0; i < N; i++)
            Arrays.fill(memo[i], -1);

        return dp(0,1);
    }

    @Test
    void test1() {
        assertEquals(7, minDifficulty(new int[]{6,5,4,3,2,1}, 2));
    }
}
