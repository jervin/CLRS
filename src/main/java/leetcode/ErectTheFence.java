package leetcode;

import lombok.val;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErectTheFence {
    // Convex hull problem
    // https://leetcode.com/explore/challenge/card/september-leetcoding-challenge-2021/636/week-1-september-1st-september-7th/3962/

    // Graham's Scan: To solve this in O(nlogn) time need to use a Graham's scan that depends on finding the min y coordinate (using x as a tiebreaker)
    //  and then collecting the rest of the vertices in the graph into a collection that is sorted by said polar angle.
    //  Think of a fan opening and then wrapping a string around it to get the hull.  Stretching the string across the nails is akin
    //  to the walking calipers. Time complexity of this is O(n*log(n)) and the space complexity is O(n).

    // Jarvis Algorithm: Another approach is the Jarvis algorithm, where you pick the left most point and then go through the entire list of
    //  points picking the one that is largest cross product, i.e. we are going to walk the hull counter-clockwise.  This
    //  algorithm's running time is O(n^2) and big oh O(m*n).  Space complexity is O(n).

    // Monotone Chain: Similar to Graham's but the points are ordered by x coordinate, with the y as a backup.  You
    //  consider the "upper" vs "lower" hull.  For the lower you go through the list of coordinates with x increasing in
    //  order, however when you reach the farthest X-coordinate, you are now doing the upper hull.  For the upper hull
    //  we consider the coordinates in reverse order.  Time Complexity: O(n*log(n)) Space Complexity: O(n)

    public double angle(int[] origin, int[] x, int[] y) {
        int cross = orientation(origin, x, y);
        double value = cross / (magnitude(origin, x) * magnitude(origin, y));
        return value;
    }

    public int orientation(int[] origin, int[] x, int[] y) {
        return (x[0] - origin[0])*(y[1] - origin[1]) - (x[1] - origin[1])*(y[0] - origin[0]);
    }

    public int magnitude(int[] origin, int[] x) {
        int[] vector = new int[] { x[0] - origin[0], x[1] - origin[1] };
        return vector[0] * vector[0] + vector[1] * vector[1];
    }


    private static int[] findOrigin(int[][] points) {
        int[] origin = points[0];
        for (int[] point : points) {
            if (point[1] < origin[1])
                origin = point;
        }
        return origin;
    }


    public int[][] outerTrees(final int[][] trees) {
        if (trees.length <= 1)
            return trees;
        int[] origin = findOrigin(trees);
        Arrays.sort(trees, (p, q) -> {
            System.out.println(ArrayUtils.toString(trees));
            // The diff is zero iff p and q are colinear relative to the origin.
            //  Can do this instead of arc sin calculations.
            //  Note: I still think you don't need to do the second orientation calculation.
            int diff = orientation(origin, p, q) - orientation(origin, q, p);
            if (diff == 0)
                return magnitude(origin, p) - magnitude(origin, q);
            return diff > 0 ? 1 : -1;
        });
        int i = trees.length - 1;
        // This takes advantage of the fact that the trees are sorted, so any point that is at the same angle
        //  as the biggest one, we make sure to include in the perimeter.
        while (i >= 0 && orientation(origin, trees[trees.length - 1], trees[i]) == 0)
            i--;
        // We are reversing the order of the sublist because we want to start at the far right to do the
        //  algorithm.
        for (int l = i + 1, h = trees.length -1; l < h; l++, h-- ) {
            int[] coord = trees[l];
            trees[l]  = trees[h];
            trees[h]  = coord;
        }
        Stack<int[]> stack = new Stack<>();
        stack.push(trees[0]);
        stack.push(trees[1]);
        for (int j = 2; j < trees.length; j++) {
            int[] top = stack.pop();
            while (orientation(stack.peek(), top, trees[j]) > 0)
                top = stack.pop();
            stack.push(top);
            stack.push(trees[j]);
        }
        return stack.toArray(new int[0][]);
    }

    int[] point(int x, int y) { return new int[] {x, y}; }

    @Test
    void testCase() {
        val solution = new ErectTheFence();
        val trees = new int[][] {{0,0}, {0,1}, {0,2}, {1,2}, {2,2}, {3,2}, {3,1}, {3,0}, {2,0}, {1,0}, {1,1}};

        val perimeter = solution.outerTrees(trees);
        System.out.println("perimeter: " + ArrayUtils.toString(perimeter));
    }


    @Test
    void testCrossProduct() {
        val solution = new ErectTheFence();
        var origin = new int[] { 0, 0 };
        var x = new int[] { 0, 0 };
        var y = new int[] { 0, 2 };
        System.out.println("ErectTheFence.testCrossProduct(): x:" + ArrayUtils.toString(x) + " X y:" + ArrayUtils.toString(y) + " = " + solution.orientation(origin, x, y));
        x = new int[] { 2, 0 };
        y = new int[] { 2, 1 };
        System.out.println("ErectTheFence.testCrossProduct(): x:" + ArrayUtils.toString(x) + " X y:" + ArrayUtils.toString(y) + " = " + solution.orientation(origin, x, y));
        x = new int[] { 2, 0 };
        y = new int[] { 2, 1 };
        System.out.println("ErectTheFence.testCrossProduct(): x:" + ArrayUtils.toString(x) + " X y:" + ArrayUtils.toString(y) + " = " + solution.orientation(origin, x, y));
        origin = new int[] { 1, 1 };
        x = new int[] { 3,1 };
        y = new int[] { 3,-1 };
        System.out.println("ErectTheFence.testCrossProduct(): x:" + ArrayUtils.toString(x) + " X y:" + ArrayUtils.toString(y) + " = " + solution.orientation(origin, x, y));
    }

    @Test
    void testMagnitude() {
        val solution = new ErectTheFence();
        assertEquals(5.0d, solution.magnitude(new int[] {1,1}, new int[] { 4, 5 }));
    }

    @Test
    void testAngle() {
        val solution = new ErectTheFence();
        var origin = new int[] { 0, 0 };
        var x = new int[] { 2, 0 };
        var y = new int[] { 2, 1 };
        System.out.println("ErectTheFence.testAngle(): x:" + ArrayUtils.toString(x) + ", y:" + ArrayUtils.toString(y) + " angle: " + solution.angle(origin, x, y));
        x = new int[] { 2, 0 };
        y = new int[] { 2, 2 };
        System.out.println("ErectTheFence.testAngle(): x:" + ArrayUtils.toString(x) + ", y:" + ArrayUtils.toString(y) + " angle: " + solution.angle(origin, x, y));
        origin = new int[] { 1, 1 };
        x = new int[] { 3,1 };
        y = new int[] { 3,-1 };
        System.out.println("ErectTheFence.testAngle(): x:" + ArrayUtils.toString(x) + ", y:" + ArrayUtils.toString(y) + " angle: " + solution.angle(origin, x, y));
    }

}
