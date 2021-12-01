package leetcode.tree;

import lombok.val;
import org.junit.jupiter.api.Test;

public class DeleteNodeBST {
    TreeNode addNode(TreeNode parent, TreeNode node) {
        if (parent == null)
            return node;
        if (node == null)
            return parent;
        if (parent.val > node.val) {
            TreeNode added = addNode(parent.left, node);
            parent.left = added;
            return parent;
        }
        TreeNode added = addNode(parent.right, node);
        parent.right = added;
        return parent;
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.right != null) {
                return addNode(root.right, root.left);
            } else {
                return root.left;
            }
        }
        if (root.val > key && root.left != null)
            root.left = deleteNode(root.left, key);
        if (root.val < key && root.right != null)
            root.right = deleteNode(root.right, key);
        return root;
    }

    @Test
    void test1() {
        val root = TreeNode.createTree(5,3,6,2,4,null,7);
        deleteNode(root, 7);
    }
}
