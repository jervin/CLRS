package leetcode.tree;

import lombok.val;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 *
 * Example 1:
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 *
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 */
public class CreateUnorderedBinaryTreeFromPostInTraversal {
    TreeNode buildBinaryTree(int[] in, int il, int ir, int[] post, int pl, int pr) {
        if (il > ir || pl > pr)
            return null;
        TreeNode node = new TreeNode(post[pr]);
        int pivot = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] != node.val)
                continue;
            pivot = i;
            break;
        }
        node.left = buildBinaryTree(in, il, pivot - 1, post, pl, pl + pivot - il - 1);
        node.right = buildBinaryTree(in, pivot + 1, ir, post, pl + pivot - il, pr - 1);
        return node;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int N = inorder.length;
        return buildBinaryTree(inorder, 0, N-1, postorder, 0, N-1);
    }

    @Test
    void test1() {
        val inorder = new int[] {9,3,15,20,7};
        val postorder = new int[] {9,15,7,20,3};

        buildTree(inorder, postorder);
    }

    @Test
    void test2() {
        val inorder = new int[] {2,1};
        val postorder = new int[] {2,1};
        buildTree(inorder, postorder);
    }
}
