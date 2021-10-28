package ch4;

import lombok.val;
import org.apache.commons.lang3.time.StopWatch;
import org.javatuples.Triplet;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Kadane's algorithm.
 */
public class MaximalSubarray {
    // From Exercise 4.1-3 in CLRS
    public Triplet<Integer, Integer, Integer> findMaxCrossingSubarray(int[] array, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = 0;
        if (mid == low) {
            leftSum = array[mid];
            maxLeft = low;
        }
        for (int i = mid; i >= low; i--) {
            sum += array[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = 0;
        if (mid == high) {
            rightSum = array[mid];
            maxRight = high;
        }
        for (int i = mid + 1; i <= high; i++) {
            sum += array[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }
        return Triplet.with(maxLeft, maxRight, leftSum + rightSum);
    }

    public Triplet<Integer, Integer, Integer> findMaximumSubarray(int[] array, int low, int high) {
        if (low == high)
            return Triplet.with(low, high, array[low]);
        int mid = (low + high) / 2;
        val left = findMaximumSubarray(array, low, mid);
        val right = findMaximumSubarray(array, mid + 1, high);
        val cross = findMaxCrossingSubarray(array, low, mid, high);
        if (left.getValue2() >= right.getValue2() && left.getValue2() >= cross.getValue2())
            return left;
        if (right.getValue2() >= left.getValue2() && right.getValue2() >= cross.getValue2())
            return right;
        return cross;
    }

    public Triplet<Integer, Integer, Integer> findMaximumSubarrayBruteForce(int[] array) {
        int maxSum = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;
        for (int i = 0; i < array.length; i++) {
            int sum = array[i];
            if (sum > maxSum) {
                left = i;
                right = i;
                maxSum = sum;
            }
            for (int j = i + 1; j < array.length; j++) {
                sum += array[j];
                if (sum > maxSum) {
                    right = j;
                    maxSum = sum;
                }
            }
        }
        return Triplet.with(left, right, maxSum);
    }

    public Triplet<Integer, Integer, Integer> findMaximumSubarrayLinear(int[] array) {
        int best = Integer.MIN_VALUE;
        int current = 0;
        int left = 0;
        int right = 0;
        int currentLeft = 0;
        for (int i = 0; i < array.length; i++) {
            current += array[i];
            if (current > best) {
                best = current;
                left = currentLeft;
                right = i;
            }
            if (current < 0) { // If current goes negative there is no way that the maximal subarray is to the left.
                current = 0;
                currentLeft = i+1;
            }
        }
        return Triplet.with(left, right, best);
    }

    public int[] sampleArray(int size) {
        val array = new int[size];
        val index = new int[] {0};
        new Random().ints(size, -1000, 2000).forEach(v -> {array[index[0]] = v; index[0] = index[0] + 1; });
        return array;
    }

    @Test
    void testSampleArray() {
        val array = sampleArray(16);
        assertEquals(16, array.length);
    }

    @Test
    void testFindMaxCrossingSubarray() {
        assertEquals(Triplet.with(0,1,5), findMaxCrossingSubarray(new int[] {1,4}, 0, 0, 1));
        assertEquals(Triplet.with(0,4,9), findMaxCrossingSubarray(new int[] {1,2,3,4,-1,-2,-3,-4}, 0, 3, 7));
        assertEquals(Triplet.with(2,4,8), findMaxCrossingSubarray(new int[] {1,-10,3,4,1,-10,3,4}, 0, 3, 7));
        assertEquals(Triplet.with(0,7,51), findMaxCrossingSubarray(new int[] {20,-10,3,4,1,-10,3,40}, 0, 3, 7));
    }

    @Test
    void testFindMaximumSubarray() {
        assertEquals(Triplet.with(0,3,10), findMaximumSubarray(new int[] {1,2,3,4,-1,-2,-3,-4}, 0,7));
        assertEquals(Triplet.with(2,4,8), findMaximumSubarray(new int[] {1,-10,3,4,1,-10,3,4}, 0, 7));
        assertEquals(Triplet.with(0,7,51), findMaximumSubarray(new int[] {20,-10,3,4,1,-10,3,40}, 0, 7));
    }

    @Test
    void testFindMaximumSubarrayLinear() {
        assertEquals(Triplet.with(0,3,10), findMaximumSubarrayLinear(new int[] {1,2,3,4,-1,-2,-3,-4}));
        assertEquals(Triplet.with(2,4,8), findMaximumSubarrayLinear(new int[] {1,-10,3,4,1,-10,3,4}));
        assertEquals(Triplet.with(0,7,51), findMaximumSubarrayLinear(new int[] {20,-10,3,4,1,-10,3,40}));
        assertEquals(Triplet.with(0,0,-1), findMaximumSubarrayLinear(new int[] {-1,-2,-3,-4,-1,-2,-3,-4}));
    }

    @Test
    void testFindMaximumSubarrayBruteForce() {
        assertEquals(Triplet.with(0,3,10), findMaximumSubarrayBruteForce(new int[] {1,2,3,4,-1,-2,-3,-4}));
        assertEquals(Triplet.with(2,4,8), findMaximumSubarrayBruteForce(new int[] {1,-10,3,4,1,-10,3,4}));
        assertEquals(Triplet.with(0,7,51), findMaximumSubarrayBruteForce(new int[] {20,-10,3,4,1,-10,3,40}));
    }

    @Test
    void findCrossOverPoint() {
        var found = false;
        for (int i = 1; i <= 17; i++) {
            val size = (int)Math.pow(2, i);
            val array = sampleArray(size);
            var startTime = System.nanoTime();
            findMaximumSubarrayBruteForce(array);
            val bruteForceTime = System.nanoTime() - startTime;
            startTime = System.nanoTime();
            findMaximumSubarray(array, 0, array.length - 1);
            val recursiveTime = System.nanoTime() - startTime;
            startTime = System.nanoTime();
            findMaximumSubarrayLinear(array);
            val linearTime = System.nanoTime() - startTime;
            System.out.println("MaximalSubarray.findCrossOverPoint(): " + size + " -> " + bruteForceTime + ", " + recursiveTime + ", " + linearTime);
        }
    }
}
