package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamCheckerTest {

    @Test
    void test1() {
        var checker = new StreamChecker(new String[] {"cd", "k", "fl"});
        assertFalse(checker.query('a'));
        assertFalse(checker.query('b'));
        assertFalse(checker.query('c'));
        assertTrue(checker.query('d'));
        assertFalse(checker.query('e'));
        assertFalse(checker.query('f'));
        assertFalse(checker.query('g'));
        assertFalse(checker.query('h'));
        assertFalse(checker.query('i'));
        assertTrue(checker.query('k'));
        assertFalse(checker.query('l'));
    }

    /**
     * ["StreamChecker","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query"]
     * [[["abaa","abaab","aabbb","bab","ab"]],["a"],["a"],["b"],["b"],["b"],["a"],["a"],["b"],["b"],["a"],["a"],["a"],["a"],["b"],["a"],["b"],["b"],["b"],["a"],["b"],["b"],["b"],["a"],["a"],["a"],["a"],["a"],["b"],["a"],["b"],["b"],["b"],["a"],["a"],["b"],["b"],["b"],["a"],["b"],["a"]]
     * Output:
     * [null,false-a,false-a,true-b,false-b,true-b,false-a,false-a,true,false,false,false,false,false,true,false,true,false,false,false,true,false,false,false,false,false,false,false,true,false,true,false,false,false,false,true,false,true,false,true,false]
     */
    @Test
    void test2() {
        var checker = new StreamChecker(new String[] {"abaa","abaab","aabbb","bab","ab"});
        assertFalse(checker.query('a'));
        assertFalse(checker.query('a'));
        assertTrue(checker.query('b'));
        assertFalse(checker.query('b'));
        assertTrue(checker.query('b'));
        assertFalse(checker.query('a'));
        assertFalse(checker.query('a'));
    }

}
