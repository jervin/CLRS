package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortedSquares {
    public int[] sortedSquares(int[] nums) {
        if (nums.length == 1)
            return new int[] {nums[0] * nums[0]};
        int[] negSquares = new int[nums.length];
        int negIndex = -1;
        int[] squares = new int[nums.length];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                negIndex++;
                negSquares[negIndex] = nums[i] * nums[i];
                continue;
            }
            int squared = nums[i] * nums[i];
            if (negIndex == -1 || squared < negSquares[negIndex]) {
                squares[index++] = squared;
                continue;
            }
            if (squared > negSquares[negIndex]) {
                squares[index++] = negSquares[negIndex--]; // Inserting neg square
                while(negIndex != -1 && negSquares[negIndex] < squared) {
                    squares[index++] = negSquares[negIndex--]; // Inserting neg square
                }
                squares[index++] = squared;
                continue;
            }
            squares[index++] = squared;
            while(negIndex != -1 && negSquares[negIndex] == squared) {
                squares[index++] = negSquares[negIndex--]; // Inserting neg square
            }
        }
        // Checking to see if we ran out of positive integers
        while (index < nums.length) {
            squares[index++] = negSquares[negIndex--]; // Inserting neg square
        }
        return squares;
    }

    @Test
    void test1() {
        val input = new int[] {-4,-1,0,3,10};
        val expected = new int[] {0,1,9,16,100};
        assertArrayEquals(expected, sortedSquares(input));
    }

    @Test
    void test2() {
        val input = new int[] {-5,-4,-3,-2,-1};
        val expected = new int[] {1,4,9,16,25};
        assertArrayEquals(expected, sortedSquares(input));
    }

    @Test
    void test3() {
        val input = new int[] {-10000,-9999,-7,-5,0,0,10000};
        val expected = new int[] {0,0,25,49,99980001,100000000,100000000};
        assertArrayEquals(expected, sortedSquares(input));
    }
}
