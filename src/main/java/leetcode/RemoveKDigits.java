package leetcode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/remove-k-digits/
 */
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        int N = num.length();
        if (k >= N)
            return "0";
        var stack = new LinkedList<Character>();
        for (char c : num.toCharArray()) {
            while (stack.size() > 0 && k > 0 && stack.peekLast() > c) {
                stack.removeLast();
                k--;
            }
            stack.add(c);
        }
        for (int i = 0; i < k; i++)
            stack.removeLast();

        var answer = new StringBuilder();
        boolean flag = true;
        for(char c : stack) {
            if (flag && c == '0') // Remove leading zeros.
                continue;
            flag = false;
            answer.append(c);
        }
        return answer.length() > 0 ? answer.toString() : "0";
    }

    @Test
    void test1() {
        assertEquals("1219", removeKdigits("1432219", 3));
    }

    @Test
    void test2() {
        assertEquals("200", removeKdigits("10200", 1));
    }

    @Test
    void test3() {
        assertEquals("11", removeKdigits("112", 1));
    }

    @Test
    void test4() {
        assertEquals("0", removeKdigits("10001", 4));
    }

    @Test
    void test5() {
        assertEquals("0", removeKdigits("1230", 3));
    }
}
