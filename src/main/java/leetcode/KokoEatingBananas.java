package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KokoEatingBananas {

    int hours(int size, int rate) {
        int div = size / rate;
        int rem = size % rate;
        return rem == 0 ? div : div + 1;
    }

    boolean check(Map<Integer, Integer> map, int rate, int remainingHours) {
        for (int size : map.keySet()) {
            if (remainingHours <= 0)
                return false;
            int pileCount = map.get(size);
            remainingHours -= hours(size, rate) * pileCount;
        }
        return remainingHours >= 0;
    }

    public int minEatingSpeedClunky(int[] piles, int h) {
        if (piles.length == 1) {
            int bananaCount = piles[0];
            int hourCount = bananaCount / h;
            int rem = bananaCount % h;
            return rem == 0 ? hourCount : hourCount + 1;
        }
        Map<Integer, Integer> map = new TreeMap<>();
        int max = Integer.MIN_VALUE;
        for (int pile : piles) {
            max = Math.max(max, pile);
            if (!map.containsKey(pile)) {
                map.put(pile, 1);
                continue;
            }
            map.put(pile, map.get(pile) + 1);
        }
        int high = max;
        int low = 1;
        int mid = (high + low)/2;
        boolean flag = false;
        while(mid > low) {
            flag = check(map, mid, h);
            if( flag ) {
                high = mid;
                mid = (high + low)/2;
                if (mid == high)
                    break;
            } else {
                low = mid;
                mid = (high + low)/2;
                if (mid == low)
                    break;
            }
        }
        return check(map, mid, h) ? mid : mid + 1;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int max = Arrays.stream(piles).max().getAsInt();
        int high = max;
        int low = 1;
        while(high > low) {
            int mid = (high + low)/2;
            var hoursSpent = Arrays.stream(piles).map( p -> (int)Math.ceil(((double)p)/mid)).sum();
            if (hoursSpent <= h)
                high = mid;
            else
                low = mid + 1;
        }
        return high;
    }

    @Test
    void test1() {
        assertEquals(4, minEatingSpeed(new int[] {3,6,7,11}, 8));
    }
    @Test
    void test2() {
        assertEquals(30, minEatingSpeed(new int[] {30,11,23,4,20}, 5));
    }
    @Test
    void test3() {
        assertEquals(2, minEatingSpeed(new int[] {312884470}, 312884469));
    }
    @Test
    void test4() {
        assertEquals(2, minEatingSpeed(new int[] {3}, 2));
    }
    @Test
    void test5() {
        assertEquals(1000000000, minEatingSpeed(new int[] {1000000000, 1000000000}, 3));
    }
}
