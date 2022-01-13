package leetcode;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinArrowsBurstBalloons {

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a,b) ->{
            if (a[0] < b[0])
                return -1;
            if (a[0] == b[0] && a[0] == b[0])
                return 0;
            if (a[0] == b[0])
                return a[1] < b[1] ? -1: 1;
            return 1;
        });
        int N = points.length;
        int answer = 0;
        var popped = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (popped[i])
                continue;
            var interval = points[i];
            answer++;
            popped[i] = true;
            int[] sharedInterval = new int[] {interval[0], interval[1]};
            for (int j = i+1; j < N; j++) {
                var balloon = points[j];
                if (balloon[0] > sharedInterval[1] || balloon[1] < sharedInterval[0]) {
                    continue;
                }
                if (!popped[j]) {
                    popped[j] = true;
                }
                sharedInterval[0] = Math.max(sharedInterval[0], balloon[0]);
                sharedInterval[1] = Math.min(sharedInterval[1], balloon[1]);
            }
        }
        return answer;
    }

    @Test
    void test1() {
        assertEquals(2, findMinArrowShots(new int[][] { {10,16}, {2,8}, {1,6}, {7,12} }));
    }
    @Test
    void test2() {
        assertEquals(2, findMinArrowShots(new int[][] {{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}}));
    }
}
