package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShiftingLetters {
    // https://leetcode.com/explore/challenge/card/september-leetcoding-challenge-2021/637/week-2-september-8th-september-14th/3968/
    /**
     * You are given a string s of lowercase english letters and an integer array shifts of the same length.
     * Call the shift() of a letter, the next letter in the alphabet (wrapping around from 'z' to 'a', basically a modulo).
     *
     * For example shift('a') = 'b', shift('t') = 'u' and shift('z') = 'a".
     *
     * Now for each of the shifts[i] = x, we want to shift the first i + 1 letters of s, x times.
     * Return the final string after all such shifts to s are applied.
     *
     * Constraints:
     * - 1 <= s.length <= 10^5
     * - s consists of lowercase English letters
     * - shifts.length == s.length
     * - 0<= shifts[i] < 10^9
     */

    public char shift(char x, int times) {
        int locationInAlphabet = x - 'a'; // 'a' is 0X0061 in UTF-8
        int shifted = (locationInAlphabet + times) % 26;
        return (char)( shifted + 'a' );
    }


    public String shiftingLetters(String s, int[] shifts) {
        char[] array = s.toCharArray();
        int shift = 0;
        for (int i = shifts.length - 1; i >= 0; i--) {
            shift += shifts[i];
            shifts[i] = shift;
        }
        for (int i = 0; i < shifts.length; i++) {
            char shifted = shift(array[i], shifts[i]);
            array[i] = shifted;
        }
        return new String(array);
    }

    @Test
    void testShift() {
        val solution = new ShiftingLetters();
        assertEquals('b', solution.shift('a', 1));
        assertEquals('e', solution.shift('d', 1));
        assertEquals('a', solution.shift('z', 1));
        assertEquals('a', solution.shift('a', 26));
        assertEquals('a', solution.shift('y', 2));
    }

    @Test
    void testExamples() {
        val solution = new ShiftingLetters();
        assertEquals("abc", solution.shiftingLetters("abc", new int[] {0,0,0}));
        assertEquals("bbc", solution.shiftingLetters("abc", new int[] {1,0,0}));
        assertEquals("bcc", solution.shiftingLetters("abc", new int[] {0,1,0}));
        assertEquals("bcd", solution.shiftingLetters("abc", new int[] {0,0,1}));
        assertEquals("ccc", solution.shiftingLetters("abc", new int[] {1,1,0}));
        assertEquals("rpl", solution.shiftingLetters("abc", new int[] {3,5,9}));
        assertEquals("gfd", solution.shiftingLetters("aaa", new int[] {1,2,3}));


    }
}
