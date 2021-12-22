package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumbersAtMostNGivenDigits {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        var Nstr = Integer.toString(n);
        var Nstrlen = Nstr.length();
        var digitsLength = digits.length;
        var result = 0;
        // Case 1: All numbers with fewer digits than length are in
        for (int i = 1; i < Nstrlen; i++)
            result += (int)Math.pow(digitsLength, i);

        // Case #2: Need to count the number of numbers with the same number of digits as the upper bound.
        //  Outer loop here is for the upperBound value, n
        for (int i = 0; i < Nstrlen; ++i) {
            var flag = false;
            var nDigit = Nstr.charAt(i);
            // Inner loop here is about the digits we are using to create numbers.
            for (String digitString : digits) {
                var digit = digitString.charAt(0);
                if (digit > nDigit)
                    break;
                // Don't have to check digits that are greater than the upperBound[i]... digits is already ordered.
                var power = Nstrlen - i - 1;
                if (digit < nDigit)
                    result += Math.pow(digitsLength, power);
                if (digit == nDigit) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                return result;
        }
        // Incrementing result by one for the case where startSame was true, this only happens if the last digit in digits
        //  is the same as the upper digit.
        return result + 1;
    }

    @Test
    void test1() {
        var digits = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        //assertEquals(90, atMostNGivenDigitSet(digits, 100));
        //assertEquals(171, atMostNGivenDigitSet(digits, 200));
        assertEquals(252, atMostNGivenDigitSet(digits, 321));
    }
}
