package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddBinary {

    public String addBinary(String a, String b) {
        StringBuilder shorter;
        StringBuilder longer;
        if (a.length() < b.length()) {
            shorter = new StringBuilder(a).reverse();
            longer = new StringBuilder(b).reverse();
        } else {
            shorter = new StringBuilder(b).reverse();
            longer = new StringBuilder(a).reverse();
        }
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = 0; i < shorter.length(); i++) {
            int s = shorter.charAt(i) == '0' ? 0 : 1;
            int l = longer.charAt(i) == '0' ? 0 : 1;
            int r = s + l + carry;
            if (r%2 == 0) {
                result.append('0');
            } else {
                result.append('1');
            }
            carry = r >= 2 ? 1 : 0;
        }
        // Now process the rest of the longer string
        for (int i = shorter.length(); i < longer.length(); i++) {
            int l = longer.charAt(i) == '0' ? 0 : 1;
            int r = l + carry;
            if (r%2 == 0) {
                result.append('0');
            } else {
                result.append('1');
            }
            carry = r >= 2 ? 1 : 0;
        }
        // If there is still a carry... add it.
        if (carry == 1)
            result.append('1');
        return result.reverse().toString();
    }

    @Test
    void test1() {
        assertEquals("111", addBinary("11", "100"));
    }
}
