package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] stops = new int[1001];
        for (int[] trip : trips) {
            stops[trip[1]] += trip[0];
            stops[trip[2]] -= trip[0];
        }
        int current = 0;
        for (int passengerCount : stops) {
            current += passengerCount;
            if (current > capacity)
                return false;
        }
        return true;
    }

    @Test
    void test1() {
        Assertions.assertFalse(carPooling( new int[][] { {2,1,5}, {3,3,7} }, 4));
        Assertions.assertTrue(carPooling( new int[][] { {9,3,4}, {9,1,7}, {4,2,4}, {7,4,5} }, 23));
    }
    @Test
    void test2() {
        Assertions.assertTrue(carPooling( new int[][] { {10,5,7}, {10,3,4}, {7,1,8}, {6,3,4} }, 24));
    }
}
