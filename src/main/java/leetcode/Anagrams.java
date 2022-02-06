package leetcode;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/submissions/
 */
public class Anagrams {

    public List<Integer> findAnagramsFast(String s, String p) {
        var answer = new LinkedList<Integer>();
        if (p.length() > s.length())
            return answer;
        int[] pcount = new int[26];
        for (char c : p.toCharArray()) {
            pcount[(int)(c -'a')]++;
        }
        int[] scount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            scount[c-'a'] = scount[c-'a'] + 1;
            if (i >= p.length()) {
                char toRemove = s.charAt(i - p.length());
                scount[(int)(toRemove - 'a')]--;
            }
            if (Arrays.equals(scount, pcount)) {
                answer.add(i - p.length() + 1);
            }
        }
        return answer;
    }

    public List<Integer> findAnagrams(String s, String p) {
        System.out.println("s.length(): " + s.length() +", p.length(): " + p.length());
        var answer = new LinkedList <Integer>();
        for (int i = 0; i < s.length(); i++) {
            var start = i - p.length() + 1;
            if (start < 0)
                continue; // We don't have enough characters to match.
            var builder = new StringBuilder(s.substring(start, i + 1));
            for (char c : p.toCharArray()) {
                var idx = builder.indexOf("" + c);
                if (idx == -1)
                    break;
                builder.deleteCharAt(idx);
            }
            if (builder.length() == 0)
                answer.add(start);
        }
        return answer;
    }

    @Test
    void test1() {
        var s = "a".repeat(20001);
        var p = "a".repeat(10000);
        var watch = Stopwatch.createStarted();
        assertEquals(10002, findAnagrams(s, p).size()); // Like 10 seconds on my laptop
        watch.stop();
        System.out.println("slow: " + watch);
        watch.reset().start();
        assertEquals(10002, findAnagramsFast(s, p).size()); // .004 seconds on my laptop
        watch.stop();
        System.out.println("fast: " + watch);
    }
}
