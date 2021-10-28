package leetcode.tree;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeNodeTest {
    @Test
    void test1() {
        val input = new Integer[] { 1,2,3,4,5 };
        val root = TreeNode.createTree(input);
        assertEquals(1, root.val);
        assertEquals(2, root.left.val);
        assertEquals(3, root.right.val);
        assertEquals(4, root.left.left.val);
        assertEquals(5, root.left.right.val);
    }

    @Test
    void test2() {
        val input = new Integer[] {4,7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null, 0,6,5,null,9,null,null,-1,-4,null,null,null,null,-2};
        val root = TreeNode.createTree(input);
    }
}
