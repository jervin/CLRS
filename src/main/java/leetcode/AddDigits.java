package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddDigits {
    // Digital Roots: https://en.wikipedia.org/wiki/Digital_root
    public int addDigits(int num) {
        if (num <= 0)
            return num;
        if (num % 9 == 0)
            return 9;
        return num % 9;
    }
    @Test
    void test1() {
        assertEquals(9, addDigits(36));
        assertEquals(1, addDigits(37));
        assertEquals(2, addDigits(38));
        assertEquals(3, addDigits(39));
    }
}
