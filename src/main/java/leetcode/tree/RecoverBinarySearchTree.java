package leetcode.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecoverBinarySearchTree {
    TreeNode prev = null;
    TreeNode node1 = null, node2 = null;
    int found = 0;

    void traverse(TreeNode node) {
        if (node == null || found >= 2)
            return;
        traverse(node.left);
        if (prev != null) {
            if (node.val < prev.val) {
                node2 = node;
                if (node1 == null)
                    node1 = prev;
                found++;
            }
        }
        prev = node;
        traverse(node.right);
    }

    public void recoverTree(TreeNode root) {
        traverse(root);

        var tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }

    @Test
    void test1() {
        var root = TreeNode.createTree(1,3, null, null, 2);
        recoverTree(root);

        assertTrue(TreeNode.compare(root, TreeNode.createTree(3,1,null, null, 2)));
    }

    @Test
    void test2() {
        var root = TreeNode.createTree(3,1,4,null, null,2, null);
        recoverTree(root);

        assertTrue(TreeNode.compare(root, TreeNode.createTree(2,1,4,null, null, 3, null)));
    }
}
