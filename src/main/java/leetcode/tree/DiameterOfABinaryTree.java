package leetcode.tree;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 */
public class DiameterOfABinaryTree {
    int diameter = 0;
    int depth(TreeNode node) {
        if (node == null)
            return 0;
        int left = depth(node.left);
        int right = depth(node.right);
        diameter = Math.max(diameter, right + left);
        return Math.max(left, right) + 1;
    }
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return 0;
        depth(root);
        return diameter;
    }

}
