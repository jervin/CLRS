package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/multiply-strings/
public class MultiplyStrings {
    public String add(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 && j >= 0) {
            int num1CharAt = (int)(num1.charAt(i) - '0');
            int num2CharAt = (int)(num2.charAt(j) - '0');
            int sum = num1CharAt + num2CharAt + carry;
            carry = sum / 10;
            builder.append((char)(sum % 10 + '0'));
            i--; j--;
        }
        while (i >= 0) {
            int charAt = (int)(num1.charAt(i) - '0');
            int sum = charAt + carry;
            carry = carry / 10;
            builder.append((char)(sum % 10 + '0'));
            i--;
        }
        while (j >= 0) {
            int charAt = (int)(num2.charAt(j) - '0');
            int sum = charAt + carry;
            carry = carry / 10;
            builder.append((char)(sum % 10 + '0'));
            j--;
        }
        while(carry > 0) {
            builder.append((char)( carry % 10 + '0'));
            carry = carry / 10;
        }
        return builder.reverse().toString();
    }
    public String multiply(int multiplier, int powerOf10, String num2) {
        StringBuilder builder = new StringBuilder();
        for( int i  = 0; i < powerOf10; i++) {
            builder.append('0');
        }
        int carry = 0;
        for (int j = num2.length() - 1; j >= 0; j--) {
            int charAt = (int)(num2.charAt(j) - '0');
            int value = charAt * multiplier + carry;
            carry = (value / 10);
            builder.append((char)( value % 10 + '0'));
        }
        while(carry > 0) {
            builder.append((char)( carry % 10 + '0'));
            carry = carry / 10;
        }
        while (builder.charAt(builder.length() - 1) == '0' && builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1 );
        }
        return builder.reverse().toString();
    }
    public String multiply(String num1, String num2) {
        String product = "0";
        int powerOf10 = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int charAt = (int)(num1.charAt(i) - '0');
            String value = multiply(charAt, powerOf10, num2);
            product = add(product, value);
            powerOf10++;
        }
        return product;
    }

    // This really is a better solution.
    public String multiplyUsingIntArray(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        if (m == 0 || n == 0 || "0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        //any number*1=number
        if ("1".equals(num1)) {
            return num2;
        }
        if ("1".equals(num2)) {
            return num1;
        }

        int[] ans = new int[m + n];//max len of the string will be m+n
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // carry over is being added to the ans
                mul += ans[i + j + 1];

                // This val is added to ans
                ans[i + j + 1] = mul % 10;
                ans[i + j] += mul / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int res : ans) {
            if (sb.length() == 0 && res == 0) {
                continue;
            }
            sb.append(res);
        }

        return sb.toString();
    }

    @Test
    void test1() {
        assertEquals("6", multiply("3", "2"));
    }
    @Test
    void test2() {
        assertEquals("81", multiply("9", "9"));
    }

    @Test
    void test3() {
        assertEquals("891", multiply("9", "99"));
    }

    @Test
    void test4() {
        assertEquals("9801", multiply("99", "99"));
    }

    @Test
    void test5() {
        assertEquals("0", multiply("0", "8100"));
    }

    @Test
    void test6() {
        assertEquals("17221554", multiply("217","79362"));
    }

    @Test
    void test7() {
        assertEquals("158724", multiply("79362", "2"));
    }
    @Test
    void test8() {
        assertEquals("1666602", multiply("79362", "21"));
    }
    @Test
    void test9() {
        assertEquals("16666020", multiply("79362", "210"));
    }
    @Test
    void test10() {
        assertEquals("555534", multiply("79362", "7"));
    }

    @Test
    void test11() {
        assertEquals("1349154", multiply("79362", "17"));
    }
}
