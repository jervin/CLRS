package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * https://leetcode.com/problems/fair-distribution-of-cookies/description/
 */

public class FairDistributionOfCookies {
    int ANSWER = Integer.MAX_VALUE;

    void traverse(int[] cookies, int idx, int[] kids) {
        if (idx == cookies.length) {
            var max = Integer.MIN_VALUE;
            for (int kid : kids) {
                max = Math.max(max, kid);
            }
            ANSWER = Math.min(ANSWER, max);
            return;
        }
        for (int i = 0; i < kids.length; i++) {
            if (kids[i] + cookies[idx] >= ANSWER)
                continue;
            kids[i] += cookies[idx];
            traverse(cookies, idx + 1, kids);
            kids[i] -= cookies[idx];
        }
    }

    public int distributeCookies(int[] cookies, int k) {
        traverse(cookies, 0, new int[k]);
        return ANSWER;
    }

    @Test
    void test1() {
        assertEquals(31, distributeCookies(new int[] {8,15,10,20,8}, 2));
    }
}
