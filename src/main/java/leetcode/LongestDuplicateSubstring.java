package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/longest-duplicate-substring/
 *
 * Given a string s, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.
 *
 * Return any duplicated substring that has the longest possible length. If s does not have a duplicated substring, the answer is "".
 *
 * Example 1:
 *
 * Input: s = "banana"
 * Output: "ana"
 * Example 2:
 *
 * Input: s = "abcd"
 * Output: ""
 */
public class LongestDuplicateSubstring {

    // This works but we need a rolling hash to get the performance required.
    public String longestDupSubstringNaive(String s) {
        int maxSize = s.length() / 2;
        // Trying the longest substrings first
        for (int size = maxSize; size > 0; size--) {
            var set = new HashSet<String>();
            for (int start = 0; start <= s.length() - size; start++) {
                var substring = s.substring(start, start + size);
                if (set.contains(substring))
                    return substring;
                set.add(substring);
            }
        }
        return "";
    }

    public String longestDupSubstring(String s) {
        int maxSize = s.length();
        // Trying the longest substrings first
        for (int size = maxSize; size > 0; size--) {
            var set = new HashSet<Long>(); // Storing a set of hashes instead.
            var hash = (long)s.charAt(0);
            var pow = 1L;
            for (int i = 1; i < size; i++) {
                hash = hash *31 + s.charAt(i);
                pow *= 31;
            }
            set.add(hash);
            for (int start = 1; start <= s.length() - size; start++) {
                char end = s.charAt(start + size - 1);
                var prehash = hash - s.charAt(start - 1) * pow;
                hash = prehash * 31 + end;
                if (!set.add(hash))
                    return s.substring(start, start + size);
            }
        }
        return "";
    }
    @Test
    void test1() {
        val input = "banana";
        assertEquals("ana", longestDupSubstring(input));
    }

    @Test
    void test2() {
        val input = "zxcvdqkfawuytt";
        assertEquals("t", longestDupSubstringNaive(input));
        assertEquals("t", longestDupSubstring("uytt"));
    }
}
