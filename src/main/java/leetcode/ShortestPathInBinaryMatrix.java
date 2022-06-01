package leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ShortestPathInBinaryMatrix {

    int[][] grid;
    record Coord(int x, int y) {};
    Map<Coord, Integer> map = new HashMap<>();

    List<Coord> adjacent(Coord coord) {
        var list = new ArrayList<Coord>();
        // 8 different options
        if (coord.y() > 0) {
            // 1. To the left
            list.add(new Coord(coord.x(), coord.y() - 1));
            // 2. To the upper left
            if (coord.x() > 0)
                list.add(new Coord(coord.x() - 1, coord.y() - 1));
            // 3. Lower Left
            if (coord.x() < grid.length - 1)
                list.add(new Coord(coord.x() + 1, coord.y() - 1));
        }
        // 4. Upper
        if (coord.x() > 0)
            list.add(new Coord(coord.x() - 1, coord.y()));
        // 5. Lower
        if (coord.x() < grid.length - 1)
            list.add(new Coord(coord.x() + 1, coord.y()));
        if (coord.y() < grid[0].length - 1) {
            // 6. Upper right
            if (coord.x() > 0)
                list.add(new Coord(coord.x() - 1, coord.y() + 1));
            // 7. to the right
            list.add(new Coord(coord.x(), coord.y() + 1));
            // 8. To the lower right
            if (coord.x() < grid.length - 1)
                list.add(new Coord(coord.x() + 1, coord.y() + 1));
        }
        return list;
    }

    void dfs(Coord coord, int depth) {
        if (coord == null)
            return;
        if (grid[coord.x()][coord.y()] == 1)
            return;
        if (map.containsKey(coord)) {
            if (map.get(coord) <= depth)
                return;
        }
        map.put(coord, depth);
        for (Coord adj : adjacent(coord)) {
            dfs(adj, depth + 1);
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        this.grid = grid;
        dfs(new Coord(0,0), 1);
        var target = new Coord(grid.length - 1, grid[0].length - 1);
        return map.getOrDefault(target, -1);
    }

    @Test
    void test1() {
        var matrix = new int[][] {{0,0,0}, {1,1,0}, {1,1,0}};
        assertEquals(4, shortestPathBinaryMatrix(matrix));
    }
}
