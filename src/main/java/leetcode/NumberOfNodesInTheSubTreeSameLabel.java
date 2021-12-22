package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/
 */

public class NumberOfNodesInTheSubTreeSameLabel {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        int[] answer = new int[n];
        boolean[][] childRelationMatrix = new boolean[n][n];

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int parent = edge[0];
            char parentChar = labels.charAt(parent);
            int child = edge[1];
            char childChar = labels.charAt(child);
            if (parentChar == childChar)
                childRelationMatrix[parent][child] = true;
        }
        for (int i = 0; i < n; i++) {
            int children = 1;
            for (int j = 0; j < n; j++) {
                if (childRelationMatrix[i][j]) {
                    children++;
                    for (boolean relation : childRelationMatrix[j]) {
                        if (relation)
                            children++;
                    }
                }
            }
            answer[i] = children;
        }
        return answer;
    }

    @Test
    void test1() {
        var answer = countSubTrees(4, new int[][] { {0,1}, {1,2}, {0,3} }, "bbbb");
        countSubTrees(7, new int[][] { {0,1}, {1,2}, {2,3}, {3,4}, {4,5}, {5,6} }, "aaabaaa");
    }
}
