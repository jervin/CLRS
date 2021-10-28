package leetcode;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TwoSum {
    // https://leetcode.com/problems/two-sum/
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Set<Integer>> keyMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (keyMap.containsKey(nums[i])) {
                keyMap.get(nums[i]).add(i);
            } else {
                Set<Integer> indices = new TreeSet<>();
                indices.add(i);
                keyMap.put(nums[i], indices);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int y = target - x; // target - x
            if (!keyMap.containsKey(y))
                continue;
            if ( x == y ) {
                if (keyMap.get(x).size() <=1)
                    continue;
                Iterator<Integer> iterator = keyMap.get(x).iterator();
                iterator.next();
                iterator.remove();
            }
            return new int[] {i, keyMap.get(y).iterator().next()};
        }
        return new int[0];
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] array = new int[] {2,7,11,15};
        int[] answer = twoSum.twoSum(array, 9);
        System.out.println("answer:" + ArrayUtils.toString(answer));

        answer = twoSum.twoSum(new int[] {3,3}, 6);
        System.out.println("answer:" + ArrayUtils.toString(answer));

        answer = twoSum.twoSum(new int[] {3,2,3}, 6);
        System.out.println("answer:" + ArrayUtils.toString(answer));

        answer = twoSum.twoSum(new int[] {0, 4, 3, 0}, 0);
        System.out.println("answer:" + ArrayUtils.toString(answer));

        answer = twoSum.twoSum(new int[] {-1,-2,-3,-4,-5}, -8);
        System.out.println("answer:" + ArrayUtils.toString(answer));
    }
}
