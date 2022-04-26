package interview.questions;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * https://leetcode.com/problems/peeking-iterator/
 *
 * Design an iterator that supports the peek operation on an existing iterator in addition to the hasNext and the next operations.
 *
 * Implement the PeekingIterator class:
 *
 * PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator iterator.
 * int next() Returns the next element in the array and moves the pointer to the next element.
 * boolean hasNext() Returns true if there are still elements in the array.
 * int peek() Returns the next element in the array without moving the pointer.
 * Note: Each language may have a different implementation of the constructor and Iterator, but they all support the int next() and boolean hasNext() functions.
 */

public class PeekingIteratorTest {

    class PeekingIterator implements Iterator<Integer> {
        Iterator<Integer> iterator;
        Integer next;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
            next = iterator.hasNext() ? iterator.next() : null;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return next;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            var answer = next;
            next = iterator.hasNext() ? iterator.next() : null;
            return answer;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }
    }


    @Test
    void test1() {

    }
}
