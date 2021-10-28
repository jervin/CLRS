package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LargeIsland {

    // Can't get it to pass the time constraints.... the LargeIslandSolution is a better solution.  It goes through
    //  the grid and marks each island with an index (starting at 2) and maintains a map of that index to the island size.
    //
    final Set<Set<Coordinate>> islands = new HashSet<>();

    public static class Coordinate {
        final int row;
        final int col;
        final int hashCode;
        public Coordinate(int row, int col) { this.row = row; this.col = col; this.hashCode = Long.valueOf(row * 31 + col).hashCode();}

        public boolean equals(Object obj) {
            if (!(obj instanceof Coordinate))
                return false;
            Coordinate coord = (Coordinate)obj;
            return coord.row == row && coord.col == col;
        }
        public int hashCode() {
            return hashCode;
        }
    }

    public void findIslandArea(int[][] grid, int row, int col, Set<Coordinate> visited) {
        if (row - 1 >= 0) {
            Coordinate coordinate = new Coordinate(row - 1, col);
            if (grid[row - 1][col] == 1 && !visited.contains(coordinate)) {
                visited.add(coordinate);
                findIslandArea(grid, row - 1, col, visited);
            }
        }
        if (row + 1 < grid.length) {
            Coordinate coordinate = new Coordinate(row + 1, col);
            if (grid[row + 1][col] == 1 && !visited.contains(coordinate)) {
                visited.add(coordinate);
                findIslandArea(grid, row + 1, col, visited);
            }
        }
        if (col - 1 >= 0) {
            Coordinate coordinate = new Coordinate(row, col - 1);
            if (grid[row][col - 1] == 1 && !visited.contains(coordinate)) {
                visited.add(coordinate);
                findIslandArea(grid, row, col - 1, visited);
            }
        }
        if (col + 1 < grid[row].length) {
            Coordinate coordinate = new Coordinate(row, col + 1);
            if (grid[row][col + 1] == 1 && !visited.contains(coordinate)) {
                visited.add(coordinate);
                findIslandArea(grid, row, col + 1, visited);
            }
        }
    }

    public int findAreaLargestIsland(int[][] grid) {
        if (grid.length == 1 && grid[0].length == 1) {
            return grid[0][0]; // If 0 area is 0 else 1
        }
        int maxArea = 0;
        Set<Coordinate> visited = new HashSet<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0)
                    continue;
                Coordinate coordinate = new Coordinate(row, col);
                if (visited.contains(coordinate))
                    continue;
                Set<Coordinate> island = new HashSet<>();
                island.add(coordinate);
                findIslandArea(grid, row, col, island);
                islands.add(island);
                maxArea = Math.max(maxArea, island.size());
                island.addAll(island);
            }
        }
        return maxArea;
    }

    public int largestIsland1(int[][] grid) {
        int maxArea = findAreaLargestIsland(grid);
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 1)
                    continue;
                Coordinate coordinate = new Coordinate(row, col);
                grid[row][col] = 1;
                Set<Coordinate> island = new HashSet<>();
                island.add(coordinate);
                findIslandArea(grid, row, col, island);
                maxArea = Math.max(maxArea, island.size());
                grid[row][col] = 0;
            }
        }
        return maxArea;
    }

    public int findAreaOfNewIsland(int[][] grid, Coordinate c) {
        int area = 1;
        Set<Set<Coordinate>> visitedIslands = new HashSet<>();
        if (c.row - 1 >= 0) {
            Coordinate coordinate = new Coordinate(c.row - 1, c.col);
            for (Set<Coordinate> island : islands) {
                if (!island.contains(coordinate) || visitedIslands.contains(island))
                    continue;
                visitedIslands.add(island);
                area += island.size();
                break;
            }
        }
        if (c.row + 1 < grid.length) {
            Coordinate coordinate = new Coordinate(c.row + 1, c.col);
            for (Set<Coordinate> island : islands) {
                if (!island.contains(coordinate) || visitedIslands.contains(island))
                    continue;
                visitedIslands.add(island);
                area += island.size();
                break;
            }
        }
        if (c.col - 1 >= 0) {
            Coordinate coordinate = new Coordinate(c.row, c.col - 1);
            for (Set<Coordinate> island : islands) {
                if (!island.contains(coordinate) || visitedIslands.contains(island))
                    continue;
                visitedIslands.add(island);
                area += island.size();
                break;
            }
        }
        if (c.col + 1 < grid[c.row].length) {
            Coordinate coordinate = new Coordinate(c.row, c.col + 1);
            for (Set<Coordinate> island : islands) {
                if (!island.contains(coordinate) || visitedIslands.contains(island))
                    continue;
                visitedIslands.add(island);
                area += island.size();
                break;
            }
        }
        return area;
    }

    public int largestIsland(int[][] grid) {
        islands.clear();
        int maxArea = findAreaLargestIsland(grid);
        if (maxArea == 0)
            return 1;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 1)
                    continue;
                Coordinate coordinate = new Coordinate(row, col);
                int area = findAreaOfNewIsland(grid, coordinate);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {{1,0,1,1,0,1,0,1,0,0,0,0,1,0,0,1,1,1,0,1,0,0,1,0,1,0,0,1,1,0,1,1,1,1,0,1,0,1,0,1,0,0,0,1,0,1,1,1,1},{0,0,0,0,1,1,1,1,1,1,1,0,1,1,1,0,0,0,1,1,1,0,0,1,1,1,1,0,0,0,1,0,1,0,1,0,1,1,1,1,1,0,1,0,0,1,0,0,0},{1,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,1,1,0,1,0,0,1,0,1,1,0,0,1,0,1,0,1,1,1,1,0,0,1,1,0,1,0,0,0,0},{0,0,0,1,0,1,0,0,0,1,1,0,1,1,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,1,1,0,0,1,0,0,1,1,1,1,1,0,0,0,1,1,0},{0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,1,0,1,0,1,0,0,0,1,1,1,0,1,0,1,0,1,0,0,0,1,1,1,1,0,1,1,0,1,0,1,0,1,1},{0,0,0,1,1,1,0,1,1,0,1,0,1,1,1,1,0,1,0,0,1,0,1,1,0,0,1,0,0,0,1,1,1,1,1,0,0,1,1,0,0,0,0,1,1,0,0,1,1},{0,0,0,0,0,1,0,0,0,0,1,1,0,0,1,1,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,1,1,0,1,0,1,0,1,0,0,0,1},{1,0,1,1,0,0,1,1,0,0,0,1,1,1,1,0,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0,1,1,0,0,0,0,0,1,1,1,1,1,1,0,1,0,1},{1,1,1,0,0,1,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,1,1,1,1,1,0,1,0,0,1,0,0,0,1,1,1},{1,0,1,0,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,1,1,1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,0,0,1,1,1,0,1},{1,1,1,0,1,1,1,1,0,0,0,1,0,0,0,0,0,0,1,1,1,0,0,0,0,1,1,1,0,0,1,0,0,1,1,1,1,1,0,0,0,1,1,0,0,1,1,0,1},{0,1,0,0,1,1,1,1,1,1,0,1,0,0,1,0,1,1,0,0,1,1,1,1,0,1,1,0,0,1,1,1,1,1,1,0,0,1,0,1,1,0,0,0,0,1,1,0,0},{0,1,1,0,0,1,0,1,0,1,1,1,0,1,0,0,0,0,1,0,1,0,0,0,1,1,1,1,0,0,0,1,0,0,1,0,1,1,0,1,1,0,1,0,1,1,1,0,0},{0,0,0,0,1,0,1,0,1,1,1,1,0,1,0,0,0,0,1,0,0,0,1,1,0,0,1,0,0,0,1,0,0,0,1,0,0,1,1,1,1,0,0,1,1,1,0,1,0},{0,1,1,0,0,1,1,0,1,0,0,0,0,0,0,0,1,1,0,1,1,0,1,0,1,0,1,1,1,1,0,1,1,1,1,0,0,0,0,1,0,1,0,0,1,0,0,0,0},{1,0,1,1,0,0,0,1,0,1,1,1,1,1,0,1,0,1,0,1,0,0,1,0,1,0,0,0,0,1,0,1,1,0,1,1,1,0,1,1,0,1,1,1,0,1,0,1,1},{0,0,1,0,0,1,0,1,0,1,0,0,1,0,0,0,1,0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,0,0,1,1,0,1,0,1,1,1,0,1,1,0},{0,1,1,0,0,0,0,0,0,1,1,0,1,0,0,1,0,0,0,1,1,0,0,0,0,0,1,1,0,1,1,1,1,1,1,0,0,1,1,1,0,1,1,1,0,0,0,1,1},{0,0,1,1,1,1,0,1,1,0,0,0,0,1,0,1,1,0,1,0,1,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,0,0,1,0,0,1,1,1,0,0,0,0,1},{1,0,1,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,0,1,0},{0,1,0,1,1,1,0,1,1,0,0,0,1,1,1,0,1,1,1,1,1,1,1,0,0,1,0,1,1,0,0,1,1,0,0,0,1,0,1,0,0,0,1,1,0,1,1,0,1},{1,1,0,0,0,1,1,1,1,1,1,1,1,0,0,1,0,1,1,1,1,0,1,1,1,1,0,1,0,0,0,1,1,1,0,1,0,0,0,1,0,0,1,1,0,0,0,0,1},{0,1,1,0,1,1,1,1,0,0,1,0,0,1,1,0,1,1,0,0,1,0,1,1,1,0,0,1,0,1,1,1,1,1,0,1,1,0,0,1,0,0,1,1,0,0,1,0,1},{0,0,0,0,0,1,1,1,0,1,0,1,0,0,0,1,1,1,1,1,0,0,0,1,0,0,1,1,0,1,1,1,1,0,1,1,0,1,1,1,0,0,0,0,0,0,1,1,1},{1,0,0,0,1,1,0,1,1,0,0,1,1,1,0,1,1,0,1,1,0,1,1,0,0,1,0,0,0,1,1,1,1,0,1,0,1,0,1,1,1,0,0,1,0,1,0,0,1},{1,0,1,0,1,0,0,1,1,1,1,0,1,1,1,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,0,1,1,0,0,1,0,1,0,1,0,0,1,1,0,0,0},{0,1,0,0,1,0,1,1,1,0,0,1,1,0,1,1,0,1,0,0,1,1,1,1,1,0,0,0,0,1,1,1,0,0,1,0,1,1,1,0,0,0,0,1,0,0,1,0,0},{0,1,0,0,1,1,0,0,1,0,0,0,0,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,0,1,0,1,1,0,1,1,0,1,1,1,1,0,0,1,0,1,1,1},{0,1,0,0,1,0,1,0,1,1,0,0,0,0,0,1,1,1,0,1,0,1,0,0,1,0,1,0,1,0,0,1,1,1,1,1,0,0,1,0,0,0,1,1,0,1,0,1,0},{1,0,1,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,1,0,0,0,1,1,1,0,1,1,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0},{0,1,0,1,0,1,0,1,0,1,1,1,1,0,1,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,0,1,1,0,1,1,0,0,0,0,0,0,1,0,0,1},{0,1,1,0,0,1,0,0,0,1,0,0,1,1,1,0,0,0,0,0,0,0,0,1,0,1,0,1,1,0,1,1,1,0,1,0,0,0,1,0,0,0,1,1,1,1,0,1,1},{0,1,1,0,0,1,0,1,1,1,0,0,1,1,0,0,0,1,1,0,1,0,0,0,0,0,0,1,1,0,1,1,1,1,0,0,0,0,1,1,0,1,0,0,0,1,1,0,0},{0,0,1,1,1,0,1,0,1,1,1,0,0,1,0,0,1,1,1,0,0,0,0,1,0,0,0,1,0,1,0,1,0,1,1,0,1,1,1,1,1,1,1,0,1,1,0,0,1},{1,1,1,1,0,0,0,0,1,0,1,0,0,1,0,0,0,0,0,0,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,1,1,0,1,0,0,1,0,0,1,1,1,1,1},{1,1,1,0,1,1,0,0,1,1,0,1,1,1,0,0,1,1,1,1,0,1,0,1,1,0,0,1,1,1,0,1,0,0,0,1,0,1,0,1,0,0,0,0,1,1,1,0,0},{1,1,1,1,0,1,0,0,1,0,1,1,1,1,0,1,0,0,0,0,0,0,1,0,1,1,0,0,0,0,1,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,1,1},{0,0,1,1,0,1,1,0,1,1,0,0,0,1,0,0,0,1,1,0,1,0,1,1,0,1,0,0,1,1,1,0,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1},{1,1,1,0,1,0,0,1,0,1,0,1,1,1,1,0,0,1,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1,1,1,0,1,1,0,1,0,0,1,1,1},{0,1,0,0,1,0,0,1,0,0,0,1,1,0,0,0,1,0,1,0,1,1,0,0,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1},{0,1,0,0,0,0,1,1,0,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,1,0,0,0,1,0,0,1,0,0,0,0,0,0,1,0,1,0,1,1,0,0,0,1,0},{0,1,1,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,1,1,1,0,1,0,1,1,1,0,1,0,0,0,1,1,1,0,1,1,1,0,0,0,0,1,1,0,1,1},{0,0,1,1,0,0,0,0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,1,1,1,1,0,1,1,1,1,0,1,1,0,0,0,1,0,0,0,1,1,1,1,1,1,0,0},{1,1,1,1,1,1,0,1,0,1,1,0,1,1,1,1,0,1,1,0,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,0,1,1,0,0,1,1,0,1,1,1,0,1,0},{1,1,0,0,0,1,1,0,0,0,0,1,1,1,1,0,1,1,0,0,1,1,1,0,0,1,0,1,0,1,1,1,0,0,0,0,1,0,0,0,1,0,0,1,1,1,0,0,0},{1,1,0,1,1,1,1,0,0,1,0,1,1,1,1,0,1,0,0,0,0,0,1,1,1,1,0,0,0,1,0,1,1,0,0,1,0,0,1,1,0,0,1,0,0,0,1,0,1},{0,1,1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,0,0,0,0,1,0,0,1,0,0,1,0,1,0,0,1,1,1,1,1,0,0,0},{1,0,0,0,0,1,0,0,1,1,0,1,1,0,0,1,0,0,0,1,1,1,1,0,1,0,1,1,1,0,1,1,1,1,0,0,0,1,0,1,1,1,1,1,0,0,0,1,1},{1,0,0,1,0,0,1,0,0,0,1,0,0,0,1,1,1,0,1,1,1,0,1,0,0,1,0,0,0,1,0,1,1,0,1,0,0,0,1,1,0,0,0,0,1,0,1,0,1}};
        LargeIsland largeIsland = new LargeIsland();
        int area = largeIsland.largestIsland1(grid);
        System.out.println("area: " + area);

//        int[][] grid1 = new int[][]{{0, 0}, {0,0}};
//        area = largeIsland.largestIsland(grid1);
//        System.out.println("area: " + area);
//
//        int[][] grid2 = new int[][]{{1,0},{0,1}};
//        area = largeIsland.largestIsland(grid2);
//        System.out.println("area: " + area);
    }
}
