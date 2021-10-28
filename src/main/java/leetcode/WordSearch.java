package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/word-search/
 *
 * This is a depth first search problem.  You need to track "where you've been"...
 */
public class WordSearch {
    public class Point {
        int x, y;
        public Point(int x, int y) { this.x = x; this.y = y; }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
        @Override public int hashCode() { return Objects.hash(x, y); }
        @Override public String toString() { return "Point{" + "x=" + x + ", y=" + y + '}'; }
    }
    public boolean checkSpot(char[][] board, Point point, String word, int wordOffset, Set<Point> pointSet) {
        if (board[point.x][point.y] != word.charAt(wordOffset))
            return false;
        pointSet.add(point);
        // Check if we are done
        if (wordOffset == word.length() - 1)
            return true;
        // Check up
        if (point.x > 0) {
            Point next = new Point(point.x - 1, point.y);
            boolean alreadyVisited = pointSet.contains(next);
            if (!alreadyVisited && checkSpot(board, next, word, wordOffset + 1, pointSet))
                return true;
            if (!alreadyVisited)
                pointSet.remove(next);
        }
        // Check down
        if (point.x < board.length - 1) {
            Point next = new Point(point.x + 1, point.y);
            boolean alreadyVisited = pointSet.contains(next);
            if (!alreadyVisited && checkSpot(board, next, word, wordOffset + 1, pointSet))
                return true;
            if (!alreadyVisited)
                pointSet.remove(next);
        }
        // Check left
        if (point.y > 0) {
            Point next = new Point(point.x, point.y - 1);
            boolean alreadyVisited = pointSet.contains(next);
            if (!alreadyVisited && checkSpot(board, next, word, wordOffset + 1, pointSet))
                return true;
            if (!alreadyVisited)
                pointSet.remove(next);
        }
        // Check right
        if (point.y < board[point.x].length - 1) {
            Point next = new Point(point.x, point.y + 1);
            boolean alreadyVisited = pointSet.contains(next);
            if (!alreadyVisited && checkSpot(board, next, word, wordOffset + 1, pointSet))
                return true;
            if (!alreadyVisited)
                pointSet.remove(next);
        }
        return false;
    }
    public boolean exist(char[][] board, String word) {
        for (int x = 0; x < board.length; x++) {
            char[] row = board[x];
            for (int y = 0; y < row.length; y++) {
                if (checkSpot(board, new Point(x,y), word, 0, new HashSet<>()))
                    return true;
            }
        }
        return false;
    }

    @Test
    void test1() {
        // [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
        val board = new char[][] { {'A','B','C','E'}, {'S','F','C','S'}, { 'A','D','E','E'} };
        val word = "ABCB";
        assertFalse(exist(board, word));
    }

    @Test
    void test2() {
        /*
         * [["A","B","C","E"],["S","F","E","S"],["A","D","E","E"]]
         * "ABCESEEEFS"
         */
        val board = new char[][] { {'A','B','C','E'}, {'S','F','E','S'}, { 'A','D','E','E'} };
        val word = "ABCESEEEFS";
        assertTrue(exist(board, word));
    }

    @Test
    void test3() {
        /*
         * [["a","a","a","a"],["a","a","a","a"],["a","a","a","a"]]
         * "aaaaaaaaaaaaa"
         */
        val board = new char[][] { {'a','a'}, {'a','a'} };
        val word = "aaaaa";
        assertFalse(exist(board, word));
    }
}
