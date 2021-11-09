package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/
 * With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
 *     word contains the first letter of puzzle.
 *     For each letter in word, that letter is in puzzle.
 *         For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage", while
 *         invalid words are "beefed" (does not include 'a') and "based" (includes 's' which is not in the puzzle).
 * Return an array answer, where answer[i] is the number of words in the given word list words that is valid with respect to the puzzle puzzles[i].
 */

public class NumberOfValidWordsForEachPuzzle {

    public Pattern getPattern(String puzzle) {
        StringBuilder builder = new StringBuilder();
        if (puzzle.length() == 1) {
            return Pattern.compile(builder.append("^[" + puzzle.charAt(0) + "]*$").toString());
        }
        builder.append("^[");
        for (int i = 0 ; i < puzzle.length(); i++) {
            builder.append("" + puzzle.charAt(i) + "");
        }
        builder.append("]*$");
        System.out.println("pattern: " + builder.toString());
        return Pattern.compile(builder.toString());
    }

    public List<Pattern> getPatterns(String... puzzles) {
        var patterns = new ArrayList<Pattern>();
        for (String puzzle : puzzles) {
            patterns.add(getPattern(puzzle));
        }
        return patterns;
    }

    public List<Integer> findNumOfValidWordsTooSlow(String[] words, String[] puzzles) {
        // This looks like a job for regular expressions.
        int[] results = new int[puzzles.length];
        var patterns = getPatterns(puzzles);
        for (int i = 0; i < results.length; i++) {
            var pattern = patterns.get(i);
            for (String word : words) {
                var matcher = pattern.matcher(word);
                boolean matches = matcher.matches();
                if (matches && word.contains("" + puzzles[i].charAt(0))) {
                    results[i] = results[i] + 1;
                }
            }
        }
        var list = new ArrayList<Integer>();
        for (int result : results)
            list.add(result);

        return list;
    }

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        var result = new ArrayList<Integer>();
        Map<Integer, Integer> wordMaskCountMap = new HashMap<>();
        for (String word : words) {
            int wordMask = getMask(word, 0);
            wordMaskCountMap.put(wordMask, wordMaskCountMap.getOrDefault(wordMask, 0) + 1);
        }

        for (String puzzle : puzzles) {
            int puzzleMask = getMask(puzzle, 1);
            int firstCharMask = 1 << (puzzle.charAt(0) - 'a');
            int subsetMask = puzzleMask;
            int count = wordMaskCountMap.getOrDefault(firstCharMask, 0);

            while (subsetMask != 0) {
                count += wordMaskCountMap.getOrDefault(subsetMask | firstCharMask, 0);
                subsetMask = (subsetMask - 1) & puzzleMask;
            }

            result.add(count);
        }

        return result;
    }

    private static int getMask(String s, int start) {
        // Only need 26 bits... should fit in an int
        int mask = 0;
        for (int i = start; i < s.length(); i++) {
            mask |= 1 << (s.charAt(i) - 'a');
        }
        return mask;
    }

    @Test
    void test1() {
        val words = new String[] { "aaaa","asas","able","ability","actt","actor","access" };
        val puzzles = new String[] { "aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz" };
        val result = findNumOfValidWords(words, puzzles);
        assertEquals(Arrays.asList(1,1,3,2,4,0), result);
    }

    @Test
    void test2() {
        val words = new String[] { "apple","pleas","please"};
        val puzzles = new String[] { "aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy" };
        val result = findNumOfValidWords(words, puzzles);
        assertEquals(Arrays.asList(0,1,3,2,0), result);
    }
}
