package codewars.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Calculator {
    // https://www.codewars.com/kata/5235c913397cbf2508000048/train/java
    public static Double evaluate(String expression) {
        // your code here
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
