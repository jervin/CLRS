package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreOfParentheses {
    public int scoreOfParentheses(String s) {
        var stack = new Stack<Object>(); // can contain integers or '('
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push("(");
                continue;
            }
            // c has to be ')'
            if (stack.peek().equals("(")) {
                stack.pop();
                var score = 1;
                while(!stack.isEmpty() && stack.peek() instanceof Number)
                    score += (int)stack.pop();
                stack.push(score);
                continue;
            }
            // Top of the stack must be a number then
            var score = 0;
            while(!stack.isEmpty() && stack.peek() instanceof Number)
                score += (int)stack.pop();
            if (!stack.isEmpty())
                stack.pop();
            score *= 2;
            stack.push(score);
        }
        var score = 0;
        while (!stack.isEmpty()) {
            if (stack.peek() == "(")
                stack.pop();
            else
                score += (int)stack.pop();
        }
        return score;
    }

    @Test
    void test1() {
        assertEquals(1, scoreOfParentheses("()"));
    }

    @Test
    void test2() {
        assertEquals(2, scoreOfParentheses("()()"));
    }

    @Test
    void test3() {
        assertEquals(4, scoreOfParentheses("(()())"));
    }

    @Test
    void test4() {
        assertEquals(5, scoreOfParentheses("(()())()"));
    }

    @Test
    void test5() {
        assertEquals(10, scoreOfParentheses("((()())())"));
    }

    @Test
    void test6() {
        assertEquals(6, scoreOfParentheses("(()(()))"));
    }

    @Test
    void test7() {
        assertEquals(3, scoreOfParentheses("()(())"));
    }
}
