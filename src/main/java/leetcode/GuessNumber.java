package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuessNumber {
    int number = 1702766719;
    int guess(int guess) {
        if( guess > number)
            return -1;
        if (guess < number)
            return 1;
        return 0;
    }

    int guessNumber(int start, int end) {
        if (start == end)
            return 1;
        int result = guess(start);
        if (result == 0)
            return start;
        result = guess(end);
        if (result == 0)
            return end;
        // This is the trick.... we allow guesses along the entirety of the range of Integers, so we got an integer overflow.
        long guessLong = ((long)end + (long)start) / 2L;
        int guess = (int)guessLong;
        result = guess(guess);
        if (result == 0)
            return guess;
        if (result == -1)
            return guessNumber(start, guess);
        return guessNumber(guess, end);
    }
    public int guessNumber(int n) {
        if (n == 1)
            return 1;
        return guessNumber(1, n);
    }

    @Test
    void test1() {
        number = 6;
        assertEquals(number, guessNumber(10));

        number = 2;
        assertEquals(number, guessNumber(2));

        number = 1702766719;
        assertEquals(number, guessNumber(2126753390));
    }

}
