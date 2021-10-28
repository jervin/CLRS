package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Subsets2 {
    // https://leetcode.com/explore/featured/card/august-leetcoding-challenge-2021/613/week-1-august-1st-august-7th/3837/
    Map<Integer, Integer> numList(int[] nums, int mask) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++ ) {
            if (mask == 0)
                break;
            if (mask %2 == 1) {
                int key = nums[i];
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
            }
            mask = mask >>> 1;
        }
        return map;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int powerSetSize = (int)Math.pow(2, nums.length);
        Set<Map<Integer, Integer>> set = new LinkedHashSet<>();
        for (int i = 0; i < powerSetSize; i++) {
            Map<Integer, Integer> map = numList(nums, i);
            if (!set.contains(map))
                set.add(map);
        }
        List<List<Integer>> list = new ArrayList<>();
        for (Map<Integer, Integer> child : set) {
            List<Integer> sequence = new ArrayList<>();
            for (int key : child.keySet()) {
                int times = child.get(key);
                for (int i = 0; i < times; i++) {
                    sequence.add(key);
                }
            }
            list.add(sequence);
        }
        return list;
    }
    public static void main(String[] args) {
        Subsets2 solution = new Subsets2();
        int[] array = new int[] {4,4,4,1,4};
        List<List<Integer>> subsets = solution.subsetsWithDup(array);
        System.out.println("Subsets2.main(): subsets:  " + subsets);
        System.out.println("Subsets2.main(): expected: [[], [1], [1, 4], [1, 4, 4], [1, 4, 4, 4], [1, 4, 4, 4, 4], [4], [4, 4], [4, 4, 4], [4, 4, 4, 4]]");
        System.out.println("Subsets2.main(): subsets: " + solution.subsetsWithDup(new int[] {0}));
        System.out.println("Subsets2.main(): subsets: " + solution.subsetsWithDup(new int[] {1,2,2}));
    }
}
