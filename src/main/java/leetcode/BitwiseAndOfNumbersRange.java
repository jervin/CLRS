package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/bitwise-and-of-numbers-range/submissions/
 */
public class BitwiseAndOfNumbersRange {
    public int rangeBitwiseAnd(int left, int right) {
        if (left == right)
            return left;
        int bitwiseAnd = left;
        for (long i = left + 1; i <= right; i++) {
            bitwiseAnd &= i;
            if (bitwiseAnd == 0)
                break;
        }
        return bitwiseAnd;
    }

    @Test
    void test1() {
        assertEquals(Integer.MAX_VALUE - 1, rangeBitwiseAnd(Integer.MAX_VALUE -1, Integer.MAX_VALUE));
    }
}
