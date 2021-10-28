package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/883/
 */

public class Palindrome {
    public boolean isPalindrome(String s) {
        String str = s.replaceAll("[^a-zA-Z0-9]+", "").toLowerCase(); // Should remove all the whitespace.
        for (int i = 0, j = str.length() - 1; i <= j; i++, j--) {
            if (str.charAt(i) != str.charAt(j))
                return false;
        }
        return true;
    }

    @Test
    void test1() {
        assertFalse(isPalindrome("race a car"));
        assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(isPalindrome("0P"));
    }
}
