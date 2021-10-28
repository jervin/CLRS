package leetcode.tree;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * Your Trie object will be instantiated and called as such:
 *  Trie obj = new Trie();
 *  obj.insert(word);
 *  boolean param_2 = obj.search(word);
 *  boolean param_3 = obj.startsWith(prefix);
 */
public class Trie {
    class Node {
        char value = 0; // Root node is null;
        boolean wordNode = false;
        List<Node> children = new ArrayList<>();

        public boolean equals(Object obj) {
            if (!(obj instanceof Node))
                return false;
            Node node = (Node)obj;
            return node.value == value;
        }
    }

    private Node root = new Node();

    public Trie() {}

    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char charAt = word.charAt(i);
            Node newNode = new Node();
            newNode.value = charAt;
            if (!node.children.contains(newNode)) {
                node.children.add(newNode);
                node = newNode;
            } else {
                int index = node.children.indexOf(newNode);
                node = node.children.get(index);
            }
            if (i == word.length() - 1)
                node.wordNode = true;
        }
    }

    public Node traverse(String word, int wordIndex, Node node) {
        if (wordIndex >= word.length())
            return null;
        char charAt = word.charAt(wordIndex);
        if (wordIndex == word.length() - 1 && node.value != 0) {
            if (charAt == node.value)
                return node;
            return null;
        }
        if (node.value == 0 || node.value == charAt) { // Means this is the root node.
            for (Node child : node.children) {
                Node traversed = traverse(word, node.value != 0 ? wordIndex + 1 : 0, child);
                if (traversed != null)
                    return traversed;
            }
        }
        return null;
    }

    public boolean search(String word) {
        Node node = traverse(word, 0, root);
        return node != null && node.wordNode;
    }

    public boolean startsWith(String prefix) {
        Node node = traverse(prefix, 0, root);
        return node != null;
    }


    @Test
    void test1() {
        val input = "apple";
        insert(input);
        assertTrue(search(input));
    }

    @Test
    void test2() {
        insert("apple");
        insert("app");
        assertTrue(search("app"));
    }

    @Test
    void test3() {
        insert("a");
        assertTrue(search("a"));
        assertTrue(startsWith("a"));
    }

}
