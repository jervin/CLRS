package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * [LeetCode - The World's Leading Online Programming Learning Platform](https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/submissions/965482897/)
 */

public class ArithmeticProgressionFromSequence {

    void merge(int[] arr, int p, int q, int r) {
        var n1 = q - p + 1;
        var n2 = r - q;
        var L = new int[n1];
        var R = new int[n2];
        for (int i = 0; i < n1; i++)
            L[i] = arr[p+i];
        for (int i = 0; i < n2; i++)
            R[i] = arr[q+i+1];
        var i = 0;
        var j = 0;
        var k = p;
        for (; k < r; k++) {
            if (i >= n1 || j >= n2)
                break;
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
        }
        while (i < n1) {
            arr[k] = L[i];
            k++;
            i++;
        }
        while (j < n2) {
            arr[k] = R[j];
            k++;
            j++;
        }
    }
    void mergeSort(int[] arr, int p, int r) {
        if (p >= r)
            return;
        int q = (p+r)/2;
        mergeSort(arr, p, q);
        mergeSort(arr, q+1, r);
        merge(arr, p, q, r);
    }
    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length == 2)
            return true;
        mergeSort(arr, 0, arr.length - 1);
        // Arrays.sort(arr);
        var diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            var delta = arr[i] - arr[i-1];
            if (diff != delta)
                return false;
        }
        return true;
    }

    @Test
    void test1() {
        assertTrue(canMakeArithmeticProgression(new int[] {3,5,1}));
    }
}
