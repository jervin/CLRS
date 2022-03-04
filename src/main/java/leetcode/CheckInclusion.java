package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckInclusion {

    public boolean checkInclusion(String s1, String s2) {
        int[] arr1 = new int[26];
        for (char c : s1.toCharArray()) {
            arr1[c - 'a']++;
        }
        int N = s2.length() - s1.length();
        for (int i = 0; i <= N; i++) {
            int[] arr2 = new int[26];
            for (int j = i; j < i + s1.length(); j++) {
                char c = s2.charAt(j);
                arr2[c - 'a']++;
            }
            boolean flag = false;
            for (int j = 0; j < 26; j++) {
                if (arr1[j] != arr2[j]) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                return true;
        }
        return false;
    }

    @Test
    void test1() {
        assertTrue(checkInclusion("adc", "dcda"));
    }
}
