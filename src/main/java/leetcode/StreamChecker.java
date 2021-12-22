package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class StreamChecker {
    class Node {
        char value;
        Node[] children = new Node[26];
        boolean complete = false;

        public Node(char value) { this.value = value; }

        public String toString() { return "" + value; }
    }

    Node root = new Node((char)0);

    Node addChar(Node node, char value) {
        if (node.children[value - 'a'] == null)
            node.children[value - 'a'] = new Node(value);
        return node.children[value - 'a'];
    }

    int maxSize = 0;

    void addWord(String word) {
        Node node = root;
        maxSize = Math.max(maxSize, word.length());
        for (int i = word.length() - 1; i >= 0; i--) {
            char value = word.charAt(i);
            node = addChar(node, value);
        }
        node.complete = true;
    }

    StringBuilder stringBuilder = new StringBuilder();

    public StreamChecker(String[] words) {
        for (String word : words) {
            addWord(word);
        }
    }

    public boolean query(char letter) {
        if(stringBuilder.length() >= maxSize){
            stringBuilder.deleteCharAt(0);
        }
        stringBuilder.append(letter);
        Node node = root;
        for (int i = stringBuilder.length() - 1; i >= 0; i--) {
            char value = stringBuilder.charAt(i);

            if (node != null)
                node = node.children[value - 'a'];

            if (node != null && node.complete)
                return true;
        }
        return false;
    }
}
