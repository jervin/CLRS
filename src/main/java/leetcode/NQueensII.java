package leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NQueensII {
    record Queen(int row, int col){}
    Set<Queen> queens = new HashSet<>();
    int N;

    boolean canPlace(Queen candidate) {
        var hit = queens.stream().filter( q ->  q.col() == candidate.col() || q.row() == candidate.row() || candidate.row() - q.row() == candidate.col() - q.col() || q.row() + q.col() == candidate.row() + candidate.col() ).findFirst();
        return !hit.isPresent();
    }

    void place(Queen candidate) {
        queens.add(candidate);
    }

    void remove(Queen deposed) {
        queens.remove(deposed);
    }
    int backtrack(int row, int count) {
        for (int col = 0; col < N; col++) {
            Queen candidate = new Queen(row, col);
            if (!canPlace(candidate))
                continue;
            place(candidate);
            if (row == N - 1)
                count++;
            else
                count = backtrack(row + 1, count);
            remove(candidate);
        }
        return count;
    }
    public int totalNQueens(int n) {
        this.N = n;
        return backtrack(0, 0);
    }

    @Test
    void test1() {
        assertEquals(2, totalNQueens(4));
    }
}
