package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int maxArea = 0;
        int row = matrix.length;
        int col = matrix[0].length;

        int[] dp = new int[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, findMaxAreaInHistogram(dp));
        }
        return maxArea;
    }

    int findMaxAreaInHistogram(int[] dp) {
        int len = dp.length;
        int[] left = new int[len];
        int[] right = new int[len];
        var stack = new Stack<Integer>();

        // Traversing left to right, find right limit
        for (int i = 0; i < len; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                left[i] = 0;
                continue;
            }
            while (!stack.isEmpty() && dp[stack.peek()] >= dp[i])
                stack.pop();
            left[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }

        stack.clear();

        // Traversing right to left, find left limit
        for (int i = len - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                stack.push(len - 1);
                right[i] = len - 1;
                continue;
            }
            while(!stack.isEmpty() && dp[stack.peek()] >= dp[i])
                stack.pop();
            right[i] = stack.isEmpty() ? len - 1 : stack.peek() - 1;
            stack.push(i);
        }

        // Traversing the array, now lets get the area.
        int maxArea = 0;
        int[] area = new int[len];
        for (int i = 0; i < len; i++) {
            area[i] = (right[i] - left[i] + 1) * dp[i];
            maxArea = Math.max(maxArea, area[i]);
        }
        return maxArea;
    }

    @Test
    void test1() {
        var input = new char[][] {{'1','1','1','0'}, {'1','1','1','1'}, {'1','0','1','0'}};
        assertEquals(6, maximalRectangle(input));
    }
}
