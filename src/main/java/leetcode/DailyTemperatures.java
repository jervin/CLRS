package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/daily-temperatures/
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * Example 1:
 *
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 *
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 *
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 *
 *
 * Constraints:
 *
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class DailyTemperatures {
    void insert(Map<Integer, Set<Integer>> map, int day, int temp) {
        for (int i = temp - 1; i >= 30; i--) {
            map.computeIfAbsent(i, k -> new TreeSet<>());
            map.get(i).add(day);
        }
    }
    public int[] dailyTemperaturesOld(int[] temperatures) {
        Map<Integer,Set<Integer>> map = new HashMap<>(70);
        for (int i = 0; i< temperatures.length; i++) {
            int temp = temperatures[i];
            insert(map, i, temp);
        }
        int[] answer = new int[temperatures.length];
        for (int i = 0; i< temperatures.length; i++) {
            int temp = temperatures[i];
            Set<Integer> days = map.get(temp);
            final int targetDay = i;
            if (days == null || days.isEmpty()) {
                answer[i] = 0;
                continue;
            }
            answer[i] = days.stream().filter( day -> day > targetDay).map(d -> d - targetDay).findFirst().orElse(0);
        }
        return answer;
    }

    public int[] dailyTemperatures(int[] temps) {
        // Lets start at the end and head back, this way we don't need to map to a set but rather a single value.
        int[] answer = new int[temps.length];
        Arrays.fill(answer, Integer.MAX_VALUE);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = temps.length-1; i >= 0;  i--) {
            map.put(temps[i], i);
            for (Map.Entry<Integer, Integer> temp : map.entrySet()) {
                if (temp.getKey() > temps[i])
                    answer[i] = Math.min(answer[i], temp.getValue() - i);
            }
        }
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == Integer.MAX_VALUE)
                answer[i] = 0;
        }
        return answer;
    }

    @Test
    void test1() {
        val input = new int[] {73,74,75,71,69,72,76,73};
        val expected = new int[] {1,1,4,2,1,1,0,0};
        assertArrayEquals(expected, dailyTemperatures(input));
    }
}
