package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/886/
 */
public class CountAndSay {
    // Pointer logic and Linked Lists... I need practice.
    public String say(String input) {
        StringBuilder builder = new StringBuilder();
        char prev = 0;
        int count = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            char charAt = input.charAt(i);
            if (prev == 0 ) {
                prev = charAt;
                count++;
                continue;
            }
            if (charAt == prev) {
                count++;
                continue;
            }
            builder.append(prev).append(Character.forDigit(count, 10));
            count = 1;
            prev = charAt;
        }
        if (count > 0) {
            builder.append(prev).append(Character.forDigit(count, 10));
        }
        return builder.reverse().toString();
    }

    public String countAndSay(int n) {
        String output = "1";
        for (int i = 2; i <= n; i++) {
            output = say(output);
        }
        return output;
    }

    @Test
    void test1() {
        assertEquals("1", countAndSay(1));
        assertEquals("11", countAndSay(2));
        assertEquals("21", countAndSay(3));
        assertEquals("1211", countAndSay(4));
        assertEquals("111221", countAndSay(5));
        assertEquals("312211", countAndSay(6));
    }
}
