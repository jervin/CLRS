package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairsOfSongsWithLengthDiv60 {
    // https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
    int noOfPairs(int n) {
        if (n <= 1)
            return 0;
        return (n*(n-1))/2;
    }
    public int numPairsDivisibleBy60(int[] time) {
        int N = time.length;
        final int[] mod60 = new int[60];
        Arrays.stream(time).map(i -> i%60).forEach(i -> mod60[i]++);
        int answer = noOfPairs(mod60[0]) + noOfPairs(mod60[30]);
        for (int i = 1; i < 30; i++) {
            int j = 60 - i;
            answer += mod60[i]*mod60[j];
        }
        return answer;
    }

    @Test
    void test1() {
        assertEquals(3, numPairsDivisibleBy60(new int[] {30,20,150,100,40}));
    }

    @Test
    void test2() {
        assertEquals(1, numPairsDivisibleBy60(new int[] {15,63,451,213,37,209,343,319}));
    }
}
