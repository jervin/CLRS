package leetcode.tree;

import com.google.common.collect.Lists;
import com.sun.source.tree.Tree;
import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode() {}
    TreeNode(int value) { this.val = value; }
    TreeNode(int value, TreeNode left, TreeNode right) {
        this.val = value;
        this.left = left;
        this.right = right;
    }

    public String toString() { return "(" + this.val + ")"; }

    // This is analogous to a breadth first or level first search.
    //  The trick is to know that there are other nodes in the level to process before continuing.
    static void createTree(TreeNode node, Integer[] tree, int[] index, int level, Map<Integer, List<TreeNode>> levelMap) {
        if (node == null || index[0] >= tree.length)
            return;
        val leftValue = tree[index[0]];
        index[0] += 1;
        val rightValue = tree[index[0]];
        index[0] += 1;
        if (!levelMap.containsKey(level))
            levelMap.put(level, Lists.newArrayList());
        val levelList = levelMap.get(level);
        if (leftValue != null) {
            node.left = new TreeNode(leftValue);
            levelList.add(node.left);
        }
        if (rightValue != null) {
            node.right = new TreeNode(rightValue);
            levelList.add(node.right);
        }
        int levelIndex = levelList.indexOf(node);
        if (levelIndex < levelList.size() - 1)
            createTree(levelList.get(levelIndex + 1), tree, index, level, levelMap);
        createTree(node.left, tree, index, level + 1, levelMap);
        createTree(node.right, tree, index, level + 1, levelMap);
    }

    public static TreeNode createTree(Integer... tree) {
        if (tree == null)
            return null;
        val root = new TreeNode(tree[0]);
        if (tree.length == 1)
            return root;
        val map = new HashMap<Integer, List<TreeNode>>();
        map.put(0, Lists.newArrayList(root));
        createTree(root, tree, new int[] {1}, 1, map);
        return root;
    }

    public static boolean compare(TreeNode left, TreeNode right) {
        System.out.println("TreeNode.compare() : left: " + left + ", right: " + right);
        if (left == null && right != null || left != null && right == null) {
            System.out.println("TreeNode.compare(): returning false");
            return false;
        }
        if (left == right)
            return true;
        if (left.val != right.val) {
            System.out.println("TreeNode.compare(): returning false");
            return false;
        }
        return compare(left.left, right.left) && compare(left.right, right.right);
    }
}
