package leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IslandPerimeter {
    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    // https://leetcode.com/problems/island-perimeter/
    public int islandPerimeter(int[][] grid, int row, int col, Set<Point> set) {
        int perimeter = 0;
        set.add(new Point(row, col));
        // Checking up
        Point up = new Point(row - 1, col);
        if (!set.contains(up)) {
            if (row == 0 || grid[row - 1][col] == 0)
                perimeter++;
            else
                perimeter += islandPerimeter(grid, row - 1, col, set);
        }
        // Checking left
        Point left = new Point(row, col - 1);
        if (!set.contains(left)) {
            if (col == 0 || grid[row][col - 1] == 0)
                perimeter++;
            else
                perimeter += islandPerimeter(grid, row, col - 1, set);
        }
        // Checking right
        Point right = new Point(row, col + 1);
        if (!set.contains(right)) {
            if (col == grid[row].length - 1 || grid[row][col + 1] == 0)
                perimeter++;
            else
                perimeter += islandPerimeter(grid, row, col + 1, set);
        }
        // Checking down
        Point down = new Point(row + 1, col);
        if (!set.contains(down)) {
            if (row == grid.length - 1 || grid[row + 1][col] == 0)
                perimeter++;
            else
                perimeter += islandPerimeter(grid, row + 1, col, set);
        }
        return perimeter;
    }

    public int islandPerimeter(int[][] grid) {
        // Step 1 Find the first bit of land
        int x = 0,y = 0;
        boolean found = false;
        for (int i = 0; i < grid.length; i++) {
            if (found)
                break;
            int[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 0)
                    continue;
                x = i;
                y = j;
                found = true;
                break;
            }
        }
        // Step 2 Recurse through all four directions to find more land and compute the perimeter.
        return islandPerimeter(grid, x, y, new HashSet<>());
    }

    @Test
    void test1() {
        var grid = new int[][] { { 0,1,0,0 }, { 1,1,1,0 }, { 0,1,0,0 }, { 1, 1, 0, 0 } };
        assertEquals(16, islandPerimeter(grid));

        grid = new int[][] { { 0,1 } };
        assertEquals(4, islandPerimeter(grid));
    }
}
