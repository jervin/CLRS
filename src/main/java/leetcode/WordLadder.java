package leetcode;

import com.google.common.io.Resources;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordLadder {

    List<String> wordList;
    List<String> targets;
    Map<String, Integer> memo;
    Map<String, List<String>> graph = new HashMap<>();
    int currentMin = Integer.MAX_VALUE;

    List<String> findTargets(String targetWord) {
        if(graph.containsKey(targetWord))
            return graph.get(targetWord);
        char[] target = targetWord.toCharArray();
        int currDistance = 1;
        List<String> targets = new ArrayList<>();
        for (String wordStr : wordList) {
            if (targetWord.equals(wordStr))
                continue;
            int distance = 0;
            char[] word = wordStr.toCharArray();
            for (int i = 0; i < word.length; i++) {
                if (word[i] == target[i])
                    continue;
                word[i] = target[i];
                distance++;
                if (distance > currDistance)
                    break;
                if (Arrays.equals(word, target)) {
                    targets.add(wordStr);
                    break;
                }
            }
        }
        graph.put(targetWord, targets);
        return targets;
    }

    void traverse(String word, int level) {
        if (level >= currentMin || memo.get(word) < level)
            return;
        memo.put(word, level);
        if (targets.contains(word)) {
            currentMin = Math.min(currentMin, level + 1);
            return;
        }
        var newTargets = findTargets(word);
        findTargets(word).forEach(t -> traverse(t, level + 1));
    }

    int traverseNoCallStack(String beginWord, String endWord) {
        memo.put(endWord, 1);
        var newTargets = findTargets(endWord);
        if (newTargets.contains(beginWord))
            return 2;
        if (newTargets.isEmpty()) {
            return findTargets(beginWord).isEmpty() ? 0 : 2;
        }
        var stack = new Stack<String>();
        int level = 2;
        stack.addAll(newTargets);
        while (!stack.empty()) {
            newTargets = new ArrayList<>();
            while (!stack.empty()) {
                var word = stack.pop();
                if (targets.contains(word)) {
                    memo.put(word, level);
                    return level + 1;
                }
                if (memo.get(word) > level) {
                    newTargets.addAll(findTargets(word));
                    memo.put(word, level);
                }
            }
            if (!stack.empty())
                break;
            stack.addAll(newTargets);
            level++;
        }
        return 0;
    }

    public int ladderLength(String beginWord, String endWord, List<String> words) {
        this.wordList = words;
        if (!wordList.contains(endWord))
            return 0;
        // 1. Find the set of target words in wordList with a Hamming distance of 1, if empty we return 0
        //    since we cannot complete the task.
        targets = findTargets(beginWord);
        if (targets.isEmpty())
            return 0;
        // 2. traverse through the "graph"
        memo = wordList.stream().collect(Collectors.toMap(Function.identity(), v -> Integer.MAX_VALUE));
        //traverse(endWord, 1);
        // 3. Collect all possible answers
        //var answer = targets.stream().mapToInt( t-> memo.get(t)).min();
        //if (answer.isEmpty())
        //    return 0;
        //return answer.getAsInt() != Integer.MAX_VALUE ? answer.getAsInt() + 1: 0;
        return traverseNoCallStack(beginWord, endWord);
    }

    @Test
    void test1() {
        assertEquals(5, ladderLength("hit", "cog", asList("hot", "dot", "dog", "lot", "log", "cog")));
    }

    @Test
    void test2() {
        assertEquals(2, ladderLength("a", "c", Arrays.asList("a", "b", "c")));
    }

    @Test
    void test3() {
        assertEquals(3, ladderLength("hot", "dog", Arrays.asList("hot", "dog", "dot")));
    }

    @Test
    void test4() {
        assertEquals(2, ladderLength("hog", "cog", Arrays.asList("cog")));
    }

    @Test @SneakyThrows
    void profile() {
        var inputStr = Resources.toString(Resources.getResource("WordLadder_input.txt"), Charset.defaultCharset());
        var wordList = Arrays.asList(inputStr.split("\\n"));
        var actual = ladderLength("nanny", "aloud", wordList);
        assertEquals(20, actual);
    }
}
