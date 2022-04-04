package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        var stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
                count[c - 'a']--;
                continue;
            }
            var idx = (int)( c - 'a');
            if (count[idx] <= 0 || stack.contains(c)) {
                var newCount = count[idx] - 1;
                count[idx] = Math.max(0, newCount);
                continue;
            }
            while (!stack.isEmpty() && stack.peek().compareTo(c) > 0 && count[stack.peek() - 'a'] > 0) {
                stack.pop();
            }
            stack.push(c);
            count[c - 'a']--;
        }
        return stack.stream().map(c -> "" + c).collect(Collectors.joining());
    }

    @Test
    void test1() {
        assertEquals("abc", removeDuplicateLetters("bcabc"));
    }
    @Test
    void test2() {
        assertEquals("acdb", removeDuplicateLetters("cbacdcbc"));
    }
    @Test
    void test3() {
        assertEquals("bac", removeDuplicateLetters("bbcaac"));
    }
    @Test
    void test4() {
        assertEquals("acb", removeDuplicateLetters("ccacbaba"));
    }
}
