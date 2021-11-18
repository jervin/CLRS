package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CombinationIterator {
    String chars;
    int offset = 0;
    int comboLength;
    List<String> combos = new ArrayList<>();
    Iterator<String> iterator;

    void backtrack(String combination, int offset) {
        if (combination.length() == comboLength) {
            combos.add(combination);
            return;
        }
        for (int i = offset; i < chars.length(); i++) {
            combination = combination + chars.charAt(i);
            backtrack(combination, i + 1);
            combination = combination.substring(0, combination.length()-1);
        }
    }

    public void init(String characters, int combinationLength) {
        this.chars = characters;
        this.comboLength = combinationLength;
        backtrack("", 0);
        this.iterator = combos.iterator();
    }

    public String next() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Test
    void test1() {
        val input = "abc";
        init(input, 2);
        assertEquals("ab", next());
        assertTrue(hasNext());
        assertEquals("ac", next());
        assertTrue(hasNext());
        assertEquals("bc", next());
        assertFalse(hasNext());
    }
}
