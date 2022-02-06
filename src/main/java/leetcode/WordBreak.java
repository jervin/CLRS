package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordBreak {
    Map<String, Boolean> memo = new HashMap<>();

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.isEmpty() || s.isBlank())
            return true;
        if (memo.containsKey(s))
            return memo.get(s);
        var matched = wordDict.stream().filter(w -> s.contains(w)).collect(Collectors.toList());
        if (matched.isEmpty()) {
            memo.put(s, false);
            return false;
        }
        for (String word : matched) {
            // What about multiple matches?  I am going to ignore the 101 possiblity right now.
            int idx = s.indexOf(word);
            var str = s;
            while(idx != -1) {
                var strs = new String[] {
                        str.substring(0, idx),
                        str.substring(idx + word.length())
                };
                var successful = true;
                for (String splitStr : strs) {
                    if (!wordBreak(splitStr, wordDict)) {
                        successful = false;
                        break;
                    }
                }
                if (successful) {
                    memo.put(s, true);
                    return true;
                }
                idx = str.indexOf(word, idx + word.length());
            }
        }
        memo.put(s, false);
        return false;
    }

    @Test
    void test1() {
        assertFalse(wordBreak("ccbb", Arrays.asList("bc", "cb")));
    }
    @Test
    void test2() {
        assertTrue(wordBreak("applepenapple", Arrays.asList("apple", "pen")));
    }
    @Test
    void test3() {
        assertFalse(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
