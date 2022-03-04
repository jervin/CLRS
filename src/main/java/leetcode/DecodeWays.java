package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DecodeWays {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0')
            return 0;
        if (s.length() == 1)
            return 1;
        int[] dp = new int[s.length()];
        dp[0] = 1;
        char prev = s.charAt(0);
        for (int i = 1; i < s.length() ; i++) {
            char c = s.charAt(i);
            var value = 10 * (prev - '0') + (c - '0');
            if (c == '0' && (prev == '0' || value > 26)) // Invalid mapping
                return 0;
            if (c == '0') {
                dp[i] = i > 1 ? dp[i - 2] : 1;
            } else if (value >= 10 && value <= 26) {
                dp[i] = dp[i-1] + (i > 2 ? dp[i-2] : 1);
            } else {
                dp[i] = dp[i-1];
            }
            prev = c;
        }
        return dp[s.length()-1];
    }
    @Test
    void test1() {
        assertEquals(0, numDecodings("0"));
        assertEquals(1, numDecodings("1"));
        assertEquals(1, numDecodings("10"));
        assertEquals(2, numDecodings("12"));
        assertEquals(0, numDecodings("100"));
        assertEquals(1, numDecodings("109"));
        assertEquals(3, numDecodings("126"));
        assertEquals(2, numDecodings("192"));
        assertEquals(3, numDecodings("112"));
        assertEquals(5, numDecodings("1123"));
        assertEquals(0, numDecodings("230"));
    }
}
