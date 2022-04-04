package leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class MaximumFrequencyStack {

    LinkedList<Integer> stack = new LinkedList<>();
    TreeMap<Integer, Set<Integer>> map = new TreeMap<>( (a, b) -> b-a );
    Map<Integer, Integer> map1 = new HashMap<>();

    public void push(int val) {
        stack.addFirst(val);
        System.out.println("push(): val: " + val);
        if (!map1.containsKey(val)) {
            map1.put(val, 1);
            var set = map.computeIfAbsent(1, HashSet::new);
            set.add(val);
        } else {
            var freq = map1.get(val);
            var set = map.computeIfAbsent(freq, HashSet::new);
            set.remove(val);
            freq++;
            map1.put(val, freq);
            set = map.computeIfAbsent(freq, HashSet::new);
            set.add(val);
        }
        System.out.println("push(): stack: " + stack);
        System.out.println("push(): map: " + map);
        System.out.println("push(): map1: " + map1);
    }

    public int pop() {
        var max = map.firstKey();
        var idx = Integer.MAX_VALUE;
        var toPop = Integer.MIN_VALUE;
        var set = map.get(max);
        for (int v : set) {
            var pos = Math.min(idx, stack.indexOf(v));
            if (pos < idx) {
                idx = pos;
                toPop = stack.get(idx);
            }
        }
        set.remove(toPop);
        if (set.isEmpty()) {
            map.remove(max);
        }
        set = map.computeIfAbsent(max - 1, HashSet::new);
        set.add(toPop);
        if (max > 1)
            map1.put(toPop, max - 1);
        else
            map1.remove(toPop);
        stack.removeFirstOccurrence(toPop);
        System.out.println("pop(): toPop: " + toPop + " stack: " + stack + " map: " + map + " map1: "  + map1);
        return toPop;
    }

    @Test
    void test1() {
        push(5); push(7); push(5); push(7); push(4); push(5);
        assertEquals(5, pop());
        assertEquals(7, pop());
        assertEquals(5, pop());
        assertEquals(4, pop());
    }
}
