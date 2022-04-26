package leetcode.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConvertBSTToGreaterTree {

    private int sum = 0; // I got stuck not using sum as an instance variable.
    public TreeNode convertBST(TreeNode root) {
        if (root == null)
            return null;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }
    @Test
    void test1() {
        var root = TreeNode.createTree(4,1,6,0,2,5,7,null,null,null,3,null,null,null,8);
        var transformed = convertBST(root);
        assertTrue(TreeNode.compare(transformed, TreeNode.createTree(30,36,21,36,35,26,15,null, null, null, 33, null, null, null, 8)));
    }

    @Test
    void test2() {
        var root = TreeNode.createTree(7);
        var transformed = convertBST(root);
        assertTrue(TreeNode.compare(transformed, TreeNode.createTree(7)));
    }

    @Test
    void test3() {
        var root = TreeNode.createTree(7, null, 8);
        var transformed = convertBST(root);
        assertTrue(TreeNode.compare(transformed, TreeNode.createTree(15,null,8)));
    }

    @Test
    void test4() {
        var root = TreeNode.createTree(7, 6, 8);
        var transformed = convertBST(root);
        assertTrue(TreeNode.compare(transformed, TreeNode.createTree(15,21,8)));
    }

    @Test
    void test5() {
        var root = TreeNode.createTree(6, 5, 7, null, null, null, 8);
        var transformed = convertBST(root);
        assertTrue(TreeNode.compare(transformed, TreeNode.createTree(21, 26, 15, null, null, null, 8)));
    }
    @Test
    void test6() {
        var root = TreeNode.createTree(4, null, 6, 5, 7, null, null, null, 8);
        var transformed = convertBST(root);
        assertTrue(TreeNode.compare(transformed, TreeNode.createTree(30, null, 21, 26, 15, null, null, null, 8)));
    }

    @Test
    void test7() {
        var root = TreeNode.createTree(4, 1, 6, null, null, 5, 7, null, null, null, 8);
        var transformed = convertBST(root);
        assertTrue(TreeNode.compare(transformed, TreeNode.createTree(30, 31, 21, null, null, 26, 15, null, null, null, 8)));
    }
}
