package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinimumDifficultyOfAJobSchedule {
    int N;
    int DAYS;
    int[] hardest;
    int[] jobs;

    int[][] memo;

    int dp(int idx, int day) {
        if (day == DAYS - 1) {
            memo[idx][day] = hardest[idx];
            return memo[idx][day];
        }
        if (memo[idx][day] != -1)
            return memo[idx][day];
        int hardestRemaining = 0;
        var answer = Integer.MAX_VALUE;
        for (int i = idx; i < N - (DAYS - day) -1; i++) {
            hardestRemaining = Math.max(hardestRemaining, jobs[i]);
            answer = Math.min(answer, hardestRemaining + dp(i+1, day + 1));
        }
        memo[idx][day] = answer;
        return answer;
    }
    public int minDifficulty(int[] jobDifficulty, int d) {
        if (d >= jobDifficulty.length)
            return -1;
        N = jobDifficulty.length;
        DAYS = d;
        hardest = new int[N];
        jobs = jobDifficulty;
        memo = new int[N][DAYS];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < d; j++)
                memo[i][j] = -1;

        int max = 0;
        for (int i = N -1 ; i >=0 ; i--) {
            max = Math.max(max, jobDifficulty[i]);
            hardest[i] = max;
        }
        return dp(0,0);
    }


    @Test
    void test1() {
        assertEquals(7, minDifficulty(new int[]{6,5,4,3,2,1}, 2));
    }
}
