package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicCalculatorII {
    // https://leetcode.com/problems/basic-calculator-ii/
    // 227. Basic Calculator II
    enum Op { PLUS, MINUS, MULT, DIV};

    Stack<Object> collectExpression(Stack<Object> tokens, Stack<Object> stack ) {
        var expression = new Stack<Object>();
        while (!tokens.empty()) {
            var pop = tokens.pop();
            if (pop == Op.PLUS || pop == Op.MINUS) {
                tokens.push(pop);
                break;
            }
            expression.push(pop);
            if (tokens.empty())
                continue;
            var op = tokens.pop();
            if (op == Op.PLUS || op == Op.MINUS) {
                tokens.push(op);
                break;
            }
            expression.push(op); // operator
        }
        return expression;
    }
    int evalExpression(Stack<Object> expression) {
        int answer = 0;
        while(!expression.empty()){
            var pop = expression.pop();
            if (pop instanceof Integer) {
                answer += ((Integer)pop).intValue();
                continue;
            }
            if (pop == Op.MULT) {
                var value = (Integer)expression.pop();
                answer = answer * value;
                continue;
            }
            if (pop == Op.DIV) {
                var value = (Integer)expression.pop();
                answer = answer / value;
                continue;
            }
        }
        return answer;
    }
    public int calculate(String s) {
        var tokens = new Stack<Object>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                if (!tokens.empty() && tokens.peek() instanceof Integer) {
                    var value = (Integer)tokens.pop();
                    value = value * 10 + (c - '0');
                    tokens.push(value);
                    continue;
                }
                var value = Integer.valueOf(((int)(c - '0')));
                tokens.push(value);
                continue;
            }
            if (c == '+')
                tokens.push(Op.PLUS);
            if (c == '-')
                tokens.push(Op.MINUS);
            if (c == '*')
                tokens.push(Op.MULT);
            if (c == '/')
                tokens.push(Op.DIV);
        }
        var stack = new Stack<Object>(); // going to collapse all the multiplications and divisions... need to first collect all a/b*c*d expressions
        while (!tokens.empty()) {
            var pop = tokens.pop();
            if (pop instanceof Integer) {
                stack.push(pop);
                continue;
            }
            if (pop == Op.MULT || pop == Op.DIV) {
                tokens.push(pop);
                tokens.push(stack.pop());
                var expression = collectExpression(tokens, stack);
                stack.push(evalExpression(expression));
                continue;
            }
            stack.push(pop); // Pushing the addition/subtraction operator
        }
        int answer = 0;
        while (!stack.empty()) {
            var pop = stack.pop();
            if (pop instanceof Integer) {
                answer += ((Integer)pop).intValue();
                continue;
            }
            if (pop == Op.PLUS) {
                answer += ((Integer)stack.pop());
                continue;
            }
            answer = answer - ((Integer)stack.pop());
        }
        return answer;
    }

    @Test
    void test1() {
        assertEquals(7, calculate("3+2*2"));
    }
    @Test
    void test2() {
        assertEquals(-2147483647, calculate("0-2147483647"));
    }
    @Test
    void test3() {
        assertEquals(8, calculate("14/3*2"));
    }
    @Test
    void test4() {
        assertEquals(1*2*3*4*5*6*7*8*9*10, calculate("1*2*3*4*5*6*7*8*9*10"));
    }
    @Test
    void test5() {
        assertEquals(6, calculate("1+2*5/3+6/4*2"));
    }
}
