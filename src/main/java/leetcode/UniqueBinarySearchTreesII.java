package leetcode;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniqueBinarySearchTreesII {
    // https://leetcode.com/explore/challenge/card/september-leetcoding-challenge-2021/636/week-1-september-1st-september-7th/3961/

    // Note: I don't understand what "structurally equal binary search trees mean.

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TreeNode)) return false;
            TreeNode treeNode = (TreeNode) o;
            return val == treeNode.val && Objects.equal(left, treeNode.left) && Objects.equal(right, treeNode.right);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(val, left, right);
        }
    }
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> trees = new ArrayList<>();

        return trees;
    }

    // Input: n = 3
    // Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
    @Test
    void testing() {
        val solution = new UniqueBinarySearchTreesII();
        var root = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3)));

        //assertEquals();
    }
}
