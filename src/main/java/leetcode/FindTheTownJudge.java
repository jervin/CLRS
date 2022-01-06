package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTheTownJudge {
    public int findJudge(int n, int[][] trust) {
        int TL = trust.length;
        if (n < 1 || TL < n-1)
            return -1;
        var nodes = new boolean[n+1];
        var count = 0;
        for (int i = 0; i < TL; i++) {
            var pair = trust[i];
            if (!nodes[pair[0]]) {
                nodes[pair[0]] = true;
                count++;
            }
        }
        if (count != n-1)
            return -1;
        var judge = -1;
        for (int i = 1; i <= n; i++) {
            if (!nodes[i]) {
                judge = i;
                break;
            }
        }
        for (int i = 0; i < TL; i++) {
            if (trust[i][1] == judge)
                nodes[trust[i][0]] = false; // Looking for everybody who trusts the judge.
        }
         for (int i=1; i <= n; i++) {
             if (nodes[i] && i != judge) // Only way nodes[i] is true is if they don't trust the judge.
                 return -1;
         }
        return judge;
    }

    @Test
    void test1() {
        assertEquals(-1, findJudge(3, new int[][] {{1,2},{2,3}}));
        assertEquals(3, findJudge(4, new int[][]{{1,3},{1,4},{2,3},{2,4},{4,3}}));
    }
}
