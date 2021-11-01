package codewars.kata;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Calculator {
    enum OP {
        rightP, leftP, plus, minus, mult, div;
        static OP match(String token) {
            if (token.equals("("))
                return leftP;
            if (token.equals(")"))
                return rightP;
            if (token.equals("+"))
                return plus;
            if (token.equals("-"))
                return minus;
            if (token.equals("*"))
                return mult;
            return div;
        }
    }
    // https://www.codewars.com/kata/5235c913397cbf2508000048/train/java
    public static Double evaluate(String expression) {
        // Tokenize
        String[] tokens = expression.split("\\s+");
        val output = new LinkedList<>();
        val operatorStack = new Stack<>();
        for (String token : tokens) {
            if (token.matches("^[0-9]*.[0-9]+$")) {
                // We have a number....
                output.add(Double.parseDouble(token));
                continue;
            }
            val op = Calculator.OP.match(token);
            if (op == OP.leftP) {
                operatorStack.push(op);
                continue;
            }
            if (op == OP.rightP) {

            }
            // while there is an operator o2 from the top of the operator stack other than the left parenthesis and o2
            // has greater precedence than op: pop o2 from the operator stack into the output queue

        }
        return -1.0;
    }

    @Test
    public void simpleLiteral() {
        assertEquals( new Double(127), Calculator.evaluate("127"), 1e-4, "simple literal");
    }

    @Test
    public void subtractionAndAddition() {
        assertEquals( new Double(5), Calculator.evaluate("2 + 3"), 1e-4, "addition");
        assertEquals( new Double(-5), Calculator.evaluate("2 - 3 - 4"), 1e-4, "subtraction");
    }

    @Test
    public void divisionAndMultiplication() {
        assertEquals( new Double(10), Calculator.evaluate("10 * 5 / 5"), 1e-4, "mixed division and multiplication");
    }

    @Test
    public void allMixed() {
        assertEquals(new Double(13), Calculator.evaluate("2 / 2 + 3 * 4"), 1e-4, "mixed");
    }

    @Test
    public void floats() {
        assertEquals( new Double(0), Calculator.evaluate("7.7 - 3.3 - 4.4"), 1e-4, "floats 1");
    }
}
