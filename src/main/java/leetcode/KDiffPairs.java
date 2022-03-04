package leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KDiffPairs {
    public int findPairs(int[] nums, int k) {
        //Arrays.sort(nums);
        var map = new TreeMap<Integer, Integer>();
        for (int num : nums) {
            map.compute(num, (key, v) -> v == null ? 1 : v + 1);
        }
        if (k == 0) {
            var zeroCount = 0;
            for (int num : map.keySet()) {
                zeroCount += map.get(num)  > 1 ? 1 : 0;
            }
            return zeroCount;
        }
        var answer = 0;
        for (int num : map.keySet()) {
            if (!map.containsKey(num + k))
                continue;
            answer++;
        }
        return answer;
    }
    @Test
    void test1() {
        assertEquals(4, findPairs(new int[] {1,2,3,4,5}, 1));
    }
    @Test
    void test2() {
        assertEquals(1, findPairs(new int[] {1,2,3,1,4,5}, 0));
    }
    @Test
    void test3() {
        assertEquals(1, findPairs(new int[] {1,1,1,1,1}, 0));
    }
}
