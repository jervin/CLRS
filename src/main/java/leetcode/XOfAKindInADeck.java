package leetcode;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class XOfAKindInADeck {
    public int gcd(int a, int b) { return b==0 ? a : gcd(b, a%b); }
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : deck) {
            map.merge(value, 1, (k,v) -> ++k);
        }
        int minGroupSize = Integer.MAX_VALUE;
        for (int size : map.values()) {
            minGroupSize = Math.min(minGroupSize, size);
        }
        if (minGroupSize == 1)
            return false;
        int gcd = minGroupSize;
        for (int size : map.values()) {
            gcd = gcd(size, gcd);
            if (gcd == 1)
                return false;
        }
        return true;
    }
    @Test
    void test1() {
        val input = new int[] {1,2,3,4,4,3,2,1};
        assertTrue(hasGroupsSizeX(input));
    }
    @Test
    void test2() {
        val input = new int[] {1,1,1,2,2,2,3,3};
        assertFalse(hasGroupsSizeX(input));
    }
    @Test
    void test3() {
        val input = new int[] {1,1,1,1,2,2,2,2,2,2};
        assertTrue(hasGroupsSizeX(input));
    }
}
