package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumFlipsAorBeqC {

    /**
     * https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/
     */

    public int minFlips(int a, int b, int c) {
        var answer = 0;
        while (c != 0 || a != 0 || b != 0) {
            var cbit = c & 1;
            var abit = a & 1;
            var bbit = b & 1;
            if (cbit == 0) {
                if (abit == 1)
                    answer++;
                if (bbit == 1)
                    answer++;
            } else {
                if (abit == 0 && bbit == 0)
                    answer++;
            }
            c >>>= 1;
            a >>>= 1;
            b >>>= 1;
        }
        return answer;
    }

    @Test
    void test1() {
        assertEquals(2, minFlips(1, 8, 3));
    }
}
