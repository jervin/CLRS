package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        var firstLength = firstList.length;
        var secondLength = secondList.length;
        if (firstLength == 0 || secondLength == 0)
            return new int[][] {};

        List<int[]> intersections = new ArrayList<>();
        int i = 0 , j = 0;
        while (i < firstLength && j < secondLength) {
            int[] first = firstList[i];
            int[] second = secondList[j];
            if (first[1] < second[0]) {
                i++;
                continue;
            }
            if (second[1] < first[0]) {
                j++;
                continue;
            }
            // This means that the intersection should not be empty/null set
            int[] intersection = new int[] { Math.max(first[0], second[0]), Math.min(first[1], second[1])};
            intersections.add(intersection);
            if(first[1] <= second[1]) {
                i++;
            }
            else {
                j++;
            }
        }
        return intersections.toArray(new int[intersections.size()][]);
    }

    @Test
    void test1() {
        val first = new int[][] {{0,2},{5,10},{13,23},{24,25}};
        val second = new int[][] {{1,5},{8,12},{15,24},{25,26}};
        intervalIntersection(first, second);
    }
}
