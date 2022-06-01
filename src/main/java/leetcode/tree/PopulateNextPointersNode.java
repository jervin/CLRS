package leetcode.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PopulateNextPointersNode {

    TreeNode previous, anchor;

    void process(TreeNode node) {
        if (node == null)
            return;
        if (previous != null)
            previous.next = node;
        else
            anchor = node;
        previous = node;
    }

    public TreeNode connect(TreeNode root) {
        if (root == null)
            return root;
        anchor = root;
        var current = anchor;

        while (anchor != null) {
            previous = null;
            current = anchor;
            anchor = null;
            while(current != null) {
                process(current.left);
                process(current.right);
                current = current.next;
            }
        }
        return root;
    }

    @Test
    void test1() {
        var root = TreeNode.createTree(1,2,3,4,5,null, 7);
        connect(root);
    }
}
