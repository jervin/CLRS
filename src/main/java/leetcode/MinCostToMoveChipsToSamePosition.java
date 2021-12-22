package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/submissions/
 *
 * We have n chips, where the position of the ith chip is position[i].
 *
 * We need to move all the chips to the same position. In one step, we can change the position of the ith chip from position[i] to:
 *
 * position[i] + 2 or position[i] - 2 with cost = 0.
 * position[i] + 1 or position[i] - 1 with cost = 1.
 * Return the minimum cost needed to move all the chips to the same position.
 */
public class MinCostToMoveChipsToSamePosition {
    public int minCostToMoveChips(int[] position) {
        int max = 0;
        for (int i = 0; i < position.length; i++) {
            max = Math.max(max, position[i]);
        }
        int[] vector = new int[max];
        for (int i = 0; i < position.length; i++) {
            vector[position[i] - 1] = vector[position[i] - 1] + 1;
        }
        int[] mod2 = new int[2];
        for (int i = 0; i < vector.length; i++) {
            if (i % 2 == 0)
                mod2[0] = mod2[0] + vector[i];
            else
                mod2[1] = mod2[1] + vector[i];
        }
        return Math.min(mod2[0], mod2[1]);
    }

    @Test
    void test1() {
        val input = new int[] { 2,2,2,3,3};
        assertEquals(2, minCostToMoveChips(input));
    }
}
