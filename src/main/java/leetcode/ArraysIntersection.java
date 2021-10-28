package leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArraysIntersection {
    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/674/
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int j = 0;
        List<Integer> intersection = new ArrayList();
        for (int i = 0; i < nums1.length; i++) {
            int n1 = nums1[i];
            while (j < nums2.length && nums2[j] < n1) {
                j++;
            }
            if (j >= nums2.length)
                break;
            int n2 = nums2[j];
            if (n2 == n1)
                intersection.add(n1);
            if (n2 <= n1)
                j++;
        }
        return intersection.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    void test1() {
        assertArrayEquals(new int [] {2,2}, intersect(new int[] {1,2,2,1}, new int[] {2,2}));
        assertArrayEquals(new int [] {2}, intersect(new int[] {1,2,2,1}, new int[] {2}));
    }
}
