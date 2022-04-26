package leetcode.tree;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinarySearchTreeIterator {
    public class BSTIterator {
        Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(TreeNode root) {
            push(root);
        }

        void push(TreeNode node) {
            stack.push(node);
            while (node.left != null) {
                stack.push(node.left);
                node = node.left;
            }
        }

        public int next() {
            var next = stack.pop();
            if( next.right != null)
                push(next.right);
            return next.val;
        }

        public boolean hasNext() {
            return !stack.empty();
        }
    }

    @Test
    void test1() {
        var root = TreeNode.createTree(7,3,15,null,null,9,20);
        var iterator = new BSTIterator(root);
        assertEquals(3, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(7, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(9, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(15, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(20, iterator.next());
        assertFalse(iterator.hasNext());
    }
}
