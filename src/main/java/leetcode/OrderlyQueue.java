package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderlyQueue {
    // https://leetcode.com/explore/challenge/card/september-leetcoding-challenge-2021/636/week-1-september-1st-september-7th/3964/

    // The trick is to realize that if k = 1 we have a ring in effect.  Rotate the first character to the last until we have gone
    //  through all the options.  If k >= 2 we get all permutations, thus sorting the string array is sufficient.
    public String orderlyQueue(String s, int k) {
        if (k ==1 ) {
            Set<String> set = new TreeSet<>();
            set.add(s);
            for (int i = 0; i < s.length(); i++) {
                s = s.substring(1) + s.charAt(0);
                set.add(s);
            }
            return set.stream().findFirst().get();
        }
        List<Character> list = new ArrayList<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            list.add(s.charAt(i));
        }
        list.sort((o1,o2) -> o1 - o2);
        StringBuilder builder = new StringBuilder();
        list.stream().forEach(c -> {builder.append(c);});
        return builder.toString();
    }

    @Test
    void testQueue() {
        val solution = new OrderlyQueue();
        assertEquals("acb", solution.orderlyQueue("cba", 1));
        assertEquals("aaabc", solution.orderlyQueue("baaca", 3));
    }
}
