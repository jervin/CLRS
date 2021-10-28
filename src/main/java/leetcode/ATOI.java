package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/884/
 */
public class ATOI {
    public int myAtoi(String s) {
        if (s.isEmpty())
            return 0;
        int index = 0;
        while(s.charAt(index) == ' ' && index < s.length() - 1)
            index++;
        if (index > s.length() - 1)
            return 0;
        char charAt = s.charAt(index);
        boolean positive = true;
        if (charAt == '-')
            positive = false;
        long absValue = 0;
        boolean hasPrefix = charAt == '-' || charAt == '+';

        String str = s.substring(!hasPrefix? index : index++);
        for (int i = hasPrefix ? 1 : 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9')
                break;
            absValue *= 10;
            int digit = (int)(str.charAt(i) - '0');
            absValue += digit;
            if (positive) {
                if (absValue >= Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
            } else {
                if (-absValue <= Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            }
        }
        return positive ? (int)absValue : (int)-absValue;
    }

    @Test
    void test0() {
        assertEquals(1, myAtoi("1"));
    }

    @Test
    void test1() {
        assertEquals(-42, myAtoi("   -42"));
    }

    @Test
    void test2() {
        assertEquals(0, myAtoi("   words and 987"));
    }
}
