package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        var split1 = version1.split("[.]");
        var split2 = version2.split("[.]");
        int N = Math.max(split1.length, split2.length);
        for (int i = 0; i < N; i++) {
            var revision1 = i < split1.length ? Integer.parseInt(split1[i]) : 0;
            var revision2 = i < split2.length ? Integer.parseInt(split2[i]) : 0;
            if (revision1 > revision2)
                return 1;
            if (revision1 < revision2)
                return -1;
        }
        return 0;
    }
    @Test
    void test1() {
        assertEquals(-1, compareVersion("0.1", "1.1"));
    }
}
