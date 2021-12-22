package leetcode;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfAtoms {

    int level = 0;

    class Num { int value = 0; }
    class Name { String value = ""; }

    interface Expression {
        default void mult(int multiplier) {}
    }
    class Element implements Expression {
        Name name = null; Num num;
        public void mult(int multipler) { num.value = num.value * multipler; }
    }
    class Expr implements Expression {
        List<Element> elements = new ArrayList<>();
        Expression expression = null;
        int level = 0;

        void addAll(Expression expression) {
            while (expression != null) {
                if (expression instanceof Element) {
                    this.elements.add(((Element)expression));
                    expression = null;
                    continue;
                }
                Expr expr = (Expr) expression;
                this.elements.addAll(expr.elements);
                expression = expr.expression;
            }
        }
        public void mult(int multiplier) {
            for (Element element : elements) {
                element.mult(multiplier);
            }
            if (expression != null)
                expression.mult(multiplier);
        }
    }
    void processExpression(Stack<Object> stack) {
        var expr = new Expr();
        expr.level = this.level;
        while (!stack.empty()) {
            var pop = stack.pop();
            if (pop instanceof Name) {
              var element = new Element();
              element.name = (Name)pop;
              element.num = new Num();
              element.num.value = 1;
              expr.elements.add(element);
            } else if (pop instanceof Element) {
                expr.elements.add((Element)pop);
            } else if (pop instanceof Expr) {
                expr.addAll((Expression)pop);
                if (((Expr)pop).level <= expr.level)
                    break;
            }
        }
        stack.push(expr);
    }
    void finish(Stack<Object> stack) {
        if (stack.empty())
            return;
        var expr = new Expr();
        while (!stack.empty()) {
            var pop = stack.pop();
            if (pop instanceof Name) {
                var element = new Element();
                element.name = (Name)pop;
                element.num = new Num();
                element.num.value = 1;
                expr.elements.add(element);
            } else if (pop instanceof Element) {
                expr.elements.add((Element)pop);
            } else if (pop instanceof Expr) {
                expr.addAll((Expression)pop);
            }
        }
        stack.push(expr);
    }
    public String countOfAtoms(String formula) {
        var stack = new Stack<Object>();
        int i = 0;
        while (i < formula.length()) {
            char c = formula.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                if (!stack.empty() && stack.peek() instanceof Name) { // Have to add a new element since 1 is implied
                    var num = new Num();
                    num.value = 1;
                    stack.push(num);
                    addElement(stack);
                }
                // Starting a new name
                var name = new Name();
                name.value = "" + c;
                stack.push(name);
                if (formula.length() <= i+1 || (!Character.isLowerCase(formula.charAt(i+1)) && !Character.isDigit(formula.charAt(i+1)))) {
                    var num = new Num(); num.value = 1;
                    stack.push(num);
                    addElement(stack);
                }
            }
            if (c >= 'a' && c <= 'z') {
                // Since formula is valid a Name instance has to be on the stack already
                var name = (Name)stack.peek();
                name.value = name.value + c;
                if (formula.length() <= i+1 || (!Character.isLowerCase(formula.charAt(i+1)) && !Character.isDigit(formula.charAt(i+1)))) {
                    var num = new Num(); num.value = 1;
                    stack.push(num);
                    addElement(stack);
                }
            }
            if (Character.isDigit(c)) {
                if (!stack.empty() && stack.peek() instanceof Num) {
                    var num = (Num)stack.peek();
                    num.value = num.value * 10 + ( c - '0');
                } else {
                    var num = new Num();
                    num.value = c - '0';
                    stack.push(num);
                }
                if (formula.length() <= i+1 || !Character.isDigit(formula.charAt(i+1))) {
                    addElement(stack);
                }
            }
            if (c == '(') {
                this.level++;
                var expr = new Expr();
                expr.level = this.level;
                stack.push(expr);
            }
            if (c == ')') {
                this.level--;
                processExpression(stack);
            }
            i++;
        }
        if (stack.size() == 1 && stack.peek() instanceof Element) {
            var pop = (Element)stack.pop();
            var expr = new Expr();
            expr.level = this.level;
            expr.elements.add(pop);
            stack.push(expr);
        }
        finish(stack);
        var answer = new TreeMap<String, Integer>();
        var expr = (Expr)stack.pop();
        for (Element element : expr.elements) {
            if (!answer.containsKey(element.name.value)) {
                answer.put(element.name.value, element.num.value);
                continue;
            }
            answer.put(element.name.value, answer.get(element.name.value) + element.num.value);
        }
        return answer.entrySet().stream().map(e -> "" + e.getKey() + (e.getValue() > 1 ? e.getValue() : "")).collect(Collectors.joining());
    }

    private void addElement(Stack<Object> stack) {
        var num = (Num) stack.pop();
        var pop = stack.pop();
        if (pop instanceof Expr) {
            int multiplier = num.value;
            ((Expr)pop).mult(multiplier);
            stack.push(pop);
            return;
        }
        if (pop instanceof Element)
            return;
        var name = (Name) pop;
        var element = new Element();
        element.name = name;
        element.num = num;
        stack.push(element);
    }

    @Test
    void test1() {
        assertEquals("H2O2", countOfAtoms("H2O2"));
        assertEquals("H2O", countOfAtoms("H2O"));
    }
    @Test
    void test2() {
        assertEquals("Be32", countOfAtoms("Be32"));
    }
    @Test
    void test3() {
        assertEquals("H2MgO2", countOfAtoms("Mg(OH)2"));
    }
    @Test
    void test4() {
        assertEquals("O3S", countOfAtoms("(SO3)"));
        assertEquals("O6S2", countOfAtoms("(SO3)2"));
        assertEquals("NO7S2", countOfAtoms("ON(SO3)2"));
        assertEquals("NO7S2", countOfAtoms("(ON(SO3)2)"));
        assertEquals("N2O14S4", countOfAtoms("(ON(SO3)2)2"));
        assertEquals("K4N2O14S4", countOfAtoms("K4(ON(SO3)2)2"));
    }
}
