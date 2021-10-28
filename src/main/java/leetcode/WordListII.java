package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/word-search-ii/submissions/
 */
public class WordListII {
    class Node {
        char value = 0; String word = null; List<Node> children = new ArrayList<>();
        public boolean equals(Object obj) {
            if (!(obj instanceof Node))
                return false;
            return ((Node)obj).value == value;
        }
    }
    void insert(Node root, String word) {
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
                node.word = word;  // Adding the word here to simplify creating the wordList
        }
    }

    void findWords(char[][]board, int row, int col, Node node, List<String> wordList) {
        if (node == null || node.value != board[row][col])
            return;
        if (node.word != null && !wordList.contains(node.word))
            wordList.add(node.word);
        board[row][col] = Character.toUpperCase(board[row][col]); // Marking as visited

        for (Node child : node.children) {
            // Check up..
            if (row > 0 && !Character.isUpperCase(board[row - 1][col]))
                findWords(board, row - 1, col, child, wordList);
            // Check down...
            if (row < board.length - 1 && !Character.isUpperCase(board[row + 1][col]))
                findWords(board, row + 1, col, child, wordList);
            // Check left
            if (col > 0 && !Character.isUpperCase(board[row][col - 1]))
                findWords(board, row, col - 1, child, wordList);
            // Check right
            if (col < board[row].length - 1 && !Character.isUpperCase(board[row][col + 1]))
                findWords(board, row, col + 1, child, wordList);
        }
        board[row][col] = Character.toLowerCase(board[row][col]);
    }

    // I am thinking do a Trie map with the words and then do a DFS search of the elements of the board.
    public List<String> findWords(char[][] board, String[] words) {
        List<String> wordList = new ArrayList<>();
        // Let's create a Trie
        Node root = new Node();
        for (int i = 0; i < words.length; i++) {
            insert(root, words[i]);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                for(Node child : root.children) {
                    findWords(board, i, j, child, wordList);
                }
            }
        }
        return wordList;
    }

    @Test
    void test1() {
        val board = new char[][] { {'a','a'} };
        val words = new String[] { "aaa" };
        assertEquals(new ArrayList<String>(), findWords(board, words));
    }
}
