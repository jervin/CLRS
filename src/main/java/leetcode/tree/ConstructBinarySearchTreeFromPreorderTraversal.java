package leetcode.tree;

import lombok.val;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {
    TreeNode build(TreeNode parent, int[] preorder, int[] index) {
        if (index[0] >= preorder.length)
            return null;
        TreeNode node = new TreeNode(preorder[index[0]]);
        index[0] = index[0] + 1;
        if (node.val < parent.val) {
            parent.left = node;
            build(node, preorder, index);
        }
        return node;
    }
    public TreeNode bstFromPreorder(int[] preorder) {
        return build(null, preorder, new int[] {0});
    }

    @Test
    void test1() {
        val preorder = new int[] {8,5,1,7,10,12};
        val expected = TreeNode.createTree(new Integer[] {8,5,10,1,7,null,12});
        val actual = bstFromPreorder(preorder);
    }
}
