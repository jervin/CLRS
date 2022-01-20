package leetcode;

import com.google.common.io.Resources;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JumpGameIV {
    int[] dp;
    int[] arr;
    Map<Integer, Set<Integer>> map = new HashMap<>();
    Set<Integer> visited = new LinkedHashSet<>();

    void dfs(int idx, int level) {
        if (visited.contains(idx) || (dp[idx] != -1 && dp[idx] <= level))
            return;
        visited.add(idx);
        dp[idx] = level + 1;
        if (idx + 1 < arr.length && !visited.contains(idx + 1))
            dfs(idx + 1, level + 1);
        if (idx - 1 >= 0 && !visited.contains(idx - 1))
            dfs(idx - 1, level + 1);
        var links = map.get(arr[idx]);
        for (int i : links) {
            if (visited.contains(i) || (i >= idx - 1 && i <= idx + 1))
                continue;
            dfs(i, level + 1);
        }
        visited.remove(idx);
    }

    public int minJumps(int[] arr) {
        this.arr = arr;
        dp = new int[arr.length];
        Arrays.fill(dp, -1);
        for (int i = 0; i < arr.length; i++) {
            var value = arr[i];
            if (!map.containsKey(value))
                map.put(value, new HashSet<Integer>());
            map.get(value).add(i);
        }
        dfs(arr.length - 1, -1);
        return dp[0];
    }

    @Test
    void test01() {
        assertEquals(2, minJumps(new int[]{100, -23, -23}));
    }

    @Test
    void test02() {
        assertEquals(3, minJumps(new int[]{100, -23, -23, 404}));
    }

    @Test
    void test03() {
        assertEquals(1, minJumps(new int[]{100, -23, -23, 404, 100}));
    }

    @Test
    void test04() {
        assertEquals(2, minJumps(new int[]{100, -23, -23, 404, 100, 23}));
    }

    @Test
    void test05() {
        assertEquals(3, minJumps(new int[]{100, -23, -23, 404, 100, 23, 23}));
    }

    @Test
    void test06() {
        assertEquals(3, minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23}));
    }

    @Test
    void test07() {
        assertEquals(4, minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3}));
    }

    @Test
    void test08() {
        assertEquals(3, minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404}));
    }

    @Test
    void test09() {
        assertEquals(0, minJumps(new int[]{404}));
    }

    @Test
    void test10() {
        assertEquals(1, minJumps(new int[]{3, 404}));
    }

    @Test
    void test11() {
        assertEquals(2, minJumps(new int[]{23, 3, 404}));
    }

    @Test
    void test12() {
        assertEquals(3, minJumps(new int[]{23, 23, 3, 404}));
    }

    @Test
    void test13() {
        assertEquals(3, minJumps(new int[]{23, 23, 23, 3, 404}));
    }

    @Test
    void test14() {
        assertEquals(4, minJumps(new int[]{100, 23, 23, 23, 3, 404}));
    }

    @Test
    void test15() {
        assertEquals(1, minJumps(new int[]{404, 100, 23, 23, 23, 3, 404}));
    }

    @Test
    void test16() {
        assertEquals(2, minJumps(new int[]{-23, 404, 100, 23, 23, 23, 3, 404}));
    }

    @Test
    void test17() {
        assertEquals(3, minJumps(new int[]{-23, -23, 404, 100, 23, 23, 23, 3, 404}));
    }

    @Test
    void test18() {
        assertEquals(3, minJumps(new int[]{100, -23, 404, 100, 23, 23, 23, 3, 404}));
    }

    @Test @SneakyThrows
    void testProfile() {
        var inputStr = Resources.toString(Resources.getResource("JumpGameIV_input.txt"), Charset.defaultCharset());
        var split = inputStr.split(",");
        var input = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            input[i] = Integer.valueOf(split[i].trim());
        }
        minJumps(input);
    }

}
