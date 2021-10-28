package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LargeIslandSolution {
    int key;
    int n;
    Map<Integer, Integer> islandSizeMap;

    public int largestIsland(int[][] grid) {
        n = grid.length;
        key = 2;
        islandSizeMap = new HashMap<>();
        int islandSize = 0;
        int maxSize = 0;

        for (int row = 0 ; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] != 1)
                    continue;
                islandSize = explore(grid, row, col, key);
                islandSizeMap.put(key++, islandSize);
                maxSize = Integer.max(maxSize, islandSize);
            }
        }
        islandSizeMap.put(0, 0);
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 0)
                    maxSize = Math.max(maxSize, getSizeWhenConnected(grid, row, col));
            }
        }
        return maxSize;
    }

    public int getSizeWhenConnected(int[][] grid, int row, int col) {
        int size = 0;
        Set<Integer> keys = new HashSet<>();
        if (row - 1 >= 0)
            keys.add(grid[row - 1][col]);
        if (row + 1 < n)
            keys.add(grid[row + 1][col]);
        if (col - 1 >= 0)
            keys.add(grid[row][col-1]);
        if (col + 1 < n)
            keys.add(grid[row][col + 1]);
        for (int key : keys)
            size += islandSizeMap.get(key);
        return size + 1;
    }

    public int explore(int[][] grid, int row, int col, int key) {
        if (row < 0 || row == n || col < 0 || col == n || grid[row][col] != 1)
            return 0;
        grid[row][col] = key;
        return explore(grid, row - 1, col, key)
                + explore(grid, row + 1, col, key)
                + explore(grid, row, col - 1, key)
                + explore(grid, row, col + 1, key) + 1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {{1,0,1,0}, {0,0,1,1}, {0,1,1,0}, {1,0,0,1}};
        LargeIslandSolution solution = new LargeIslandSolution();
        int area = solution.largestIsland(grid);
        System.out.println("area: " + area);
    }
}
