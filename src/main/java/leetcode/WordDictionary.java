package leetcode;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordDictionary {
    class TrieNode {
        char c;
        boolean isWord = false;
        TrieNode[] children = new TrieNode[26];

    }
    final TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode parent = root;
        int N = word.length();
        for(int i = 0; i < N; i++) {
            char c = word.charAt(i);
            int index = (int)( c - 'a');
            if (parent.children[index] == null) {
                parent.children[index] = new TrieNode();
                parent.children[index].c = c;
            }
            parent = parent.children[index];
        }
        parent.isWord = true;
    }

    public boolean search(String word) {
        return Arrays.stream(root.children).filter(child -> child != null && search(word, child, 0)).findFirst().isPresent();
    }
    boolean search(String word, TrieNode node, int idx) {
        if (node == null)
            return word == null || word.isEmpty();
        if (word == null || idx >= word.length())
            return node.isWord;
        var c = word.charAt(idx);
        if (c == '.') {
            if (idx >= word.length() - 1)
                return node.isWord;
            return Arrays.stream(node.children).filter(child -> child != null && search(word, child, idx + 1)).findFirst().isPresent();
        }
        if (c != node.c)
            return false;
        if (idx >= word.length() - 1)
            return node.isWord;
        return Arrays.stream(node.children).filter(child -> child != null && search(word, child, idx + 1)).findFirst().isPresent();
    }

    @Test
    void test1() {
        addWord("a");
        addWord("a");
        assertTrue(search("."));
        assertTrue(search("a"));
        assertFalse(search("aa"));
        assertFalse(search(".a"));
        assertFalse(search("a."));
    }

    @Test
    void test2() {
        addWord("at");
        addWord("and");
        addWord("an");
        addWord("add");
        addWord("bat");
        assertTrue(search(".at"));
    }

}
