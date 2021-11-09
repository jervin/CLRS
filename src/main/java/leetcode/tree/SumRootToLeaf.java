package leetcode.tree;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumRootToLeaf {
    public List<String> traverse(TreeNode node) {
        if (node == null)
            return new ArrayList<String>();
        var left = traverse(node.left);
        var right = traverse(node.right);
        if (node.left == null && node.right == null) {
            // leaf node
            var numbers = new ArrayList<String>();
            numbers.add( "" + node.val);
            return numbers;
        }
        // non-leaf node
        left.addAll(right);
        var newNumbers = left.stream().map( n -> "" + node.val + n).collect(Collectors.toList());
        return newNumbers;
    }

    public int sumNumbers(TreeNode root) {
        var numbers = traverse(root);
        return numbers.stream().mapToInt(Integer::valueOf).sum();
    }

    @Test
    void test1() {
        val input = TreeNode.createTree(1,5,1,null,null,null,6);
        assertEquals(131, sumNumbers(input));
    }
}
