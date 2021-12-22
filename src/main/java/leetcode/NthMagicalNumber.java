package leetcode;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class NthMagicalNumber {
    public int gcd(int a, int b) { return b==0 ? a : gcd(b, a%b); }

    long MOD = 1000000007;
    public int nthMagicalNumber(int n, int a, int b) {

        long left = Math.min(a, b);
        long right = n*left;
        int gcd = gcd(a,b);

        long lcm = (a*b)/gcd;

        while (left < right) {
            long mid = left + (right - left)/2;
            long value = (mid/a) + (mid/b) - (mid/lcm);
            if (value < n)
                left = mid + 1;
            else
                right = mid;
        }
        return (int)(left % MOD);
    }

    @Test
    void test1() {
        assertEquals(2, nthMagicalNumber(1,2,3));
        assertEquals(6, nthMagicalNumber(4,2,3));
        assertEquals(10, nthMagicalNumber(5,2,4));
    }

    @Test
    void test2() {
        assertTimeoutPreemptively(Duration.ofMillis(30000), () -> nthMagicalNumber(1000000000 ,40000, 40000));
        assertEquals(999720007, nthMagicalNumber(1000000000, 40000, 40000));
    }

}
