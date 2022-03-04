package leetcode.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaximumWidthOfABinaryTree {
    int answer = Integer.MIN_VALUE;
    List<Integer> levels = new ArrayList<>();

    void traverse(TreeNode node, int level, int idx) {
        if (node == null) {
            return;
        }
        if (level >= levels.size()) {
            levels.add(idx);
        }
        answer = Math.max(answer, idx - levels.get(level) + 1);
        traverse(node.left, level + 1, 2*idx);
        traverse(node.right, level + 1, 2*idx + 1);
    }

    public int widthOfBinaryTree(TreeNode root) {
        traverse(root, 0, 1);
        System.out.println("levels:" + levels);
        return answer;
    }

    @Test
    void test1() {
        var tree = TreeNode.createTree(1,1,1,1,1,1,1,null,null,null,1,null,null,null,null,2,2,2,2,2,2,2,null,2,null,null,2,null,2);
        assertEquals(8, widthOfBinaryTree(tree));
    }
}
