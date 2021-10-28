package leetcode;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// From leetcode challenge: https://leetcode.com/explore/challenge/card/september-leetcoding-challenge-2021/636/week-1-september-1st-september-7th/3965/
public class SlowestKey {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int n = releaseTimes.length;
        char key = keysPressed.charAt(0);
        int duration = releaseTimes[0];
        for (int i = 1; i < n; i++) {
            int diff = releaseTimes[i] - releaseTimes[i - 1];
            if (diff > duration) {
                duration = diff;
                key = keysPressed.charAt(i);
            } else if (diff == duration)
                key = keysPressed.charAt(i) > key ? keysPressed.charAt(i) : key;
        }
        return key;
    }

    @Test
    void testSpuda() {
        val solution = new SlowestKey();
        val releaseTimes = new int[] {12,23,36,46,62};
        val keysPressed = "spuda";

        assertEquals('a', solution.slowestKey(releaseTimes, keysPressed));
    }
}
