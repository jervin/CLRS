package leetcode.tree;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/path-sum-iii/
 */
public class PathSumIII {
    int numOfPaths = 0;
    int targetSum = 0;

    void addToMap(Map<Integer, Integer> child, Map<Integer, Integer> map, int nodeVal) {
        for (Integer sum : child.keySet()) {
            int newSum = nodeVal + sum;
            if (newSum == targetSum) {
                numOfPaths += child.get(sum);
            }
            if (!map.containsKey(newSum))
                map.put(newSum, child.get(sum));
            else
                map.put(newSum, child.get(sum));
        }
    }

    Map<Integer, Integer> traverse(TreeNode node) {
        if (node == null)
            return new HashMap<>();
        Map<Integer, Integer> left = traverse(node.left);
        Map<Integer, Integer> right = traverse(node.right);
        Map<Integer, Integer> map = new HashMap<>();
        addToMap(left, map, node.val);
        addToMap(right, map, node.val);
        if (map.containsKey(node.val))
            map.put(node.val, map.get(node.val) + 1);
        else
            map.put(node.val, 1);
        if(node.val == targetSum)
            numOfPaths++;
        return map;
    }

    public int pathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        traverse(root);
        return numOfPaths;
    }

    @Test
    void test1() {
        val tree = TreeNode.createTree(10,5,-3,3,2,null,11,3,-2,null,1);
        assertEquals(3, pathSum(tree, 8));
    }

    @Test
    void test2() {
        val tree = TreeNode.createTree(1,0,1,1,2,0,-1,0,1,-1,0,-1,0,1,0);
        assertEquals(13, pathSum(tree, 2));
    }

    @Test
    void test3() {
        val tree = TreeNode.createTree(2,-1,0);
        assertEquals(2, pathSum(tree, 2));
    }

    @Test
    void test4() {
        val tree = TreeNode.createTree(1,0,1);
        assertEquals(1, pathSum(tree, 2));
    }

    @Test
    void test5() {
        val tree = TreeNode.createTree(0,1,2,0,1,-1,0);
        assertEquals(5, pathSum(tree, 2));
    }

    @Test
    void test6() {
        val tree = TreeNode.createTree(0,-1,0);
        assertEquals(0, pathSum(tree, 2));
    }

    @Test
    void test7() {
        val tree = TreeNode.createTree(-1,1,0);
        assertEquals(0, pathSum(tree, 2));
    }

    @Test
    void testTraverse1() {
        val tree = TreeNode.createTree(0,-1,0);
        val expected = new HashMap<Integer,Integer>();
        expected.put(0,1);
        expected.put(-1,1);
        this.targetSum = 2;
        assertEquals(expected, traverse(tree));
    }

    @Test
    void testTraverse2() {
        val tree = TreeNode.createTree(-1,1,0);
        val expected = new HashMap<Integer,Integer>();
        expected.put(0,1);
        expected.put(-1,1);
        this.targetSum = 2;
        assertEquals(expected, traverse(tree));
    }

    @Test
    void test8() {
        val tree = TreeNode.createTree(1,0,-1,-1,0,1,0);
        assertEquals(0, pathSum(tree, 2));
    }
}
