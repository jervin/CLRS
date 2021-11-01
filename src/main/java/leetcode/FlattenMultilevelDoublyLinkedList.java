package leetcode;

import lombok.val;
import org.junit.jupiter.api.Test;

public class FlattenMultilevelDoublyLinkedList {

    public class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }


    public Node flatten(Node node) {
        return node;
    }

    public Node traverse(Node node) {
        if( node == null)
            return null;
        Node child = traverse(node.child);
        Node next = node.next;
        Node nxt = traverse(node.next);
        if (node.child != null) {
            node.next = node.child;
            node.child = null;
            node.next.prev = node;
            if (next != null) {
                next.prev = child;
                child.next = next;
            }
        }
        if (nxt != null)
            return nxt;
        if (child != null)
            return child;
        return node;
    }

    @Test
    void test1() {
        val head = new Node();
        head.val = 1;
        var node = new Node();
        node.val = 2;
        head.next = node;
        node.prev = head;
        var prev = node;
        node = new Node();
        node.val = 3;
        prev.next = node;
        node.prev = prev;
        prev = node;

        // Adding children Level 1
        var child = new Node();
        child.val = 7;
        node.child = child;
        var prevChild = child;
        child = new Node();
        child.val = 8;
        child.prev = prevChild;
        prevChild.next = child;
        prevChild = child;

        // Adding children Level 2
        var child2 = new Node();
        child2.val = 11;
        child.child = child2;
        var prevChild2 = child2;
        child2 = new Node();
        child2.val = 12;
        child2.prev = prevChild2;
        prevChild2.next = child2;

        // Back to Level 1
        child = new Node();
        child.val = 9;
        prevChild.next = child;
        child.prev = prevChild;
        prevChild = child;
        child = new Node();
        child.val = 10;
        child.prev = prevChild;
        prevChild.next = child;

        // Back to the parent list
        node = new Node();
        node.val = 4;
        node.prev = prev;
        prev.next = node;
        prev = node;

        node = new Node();
        node.val = 5;
        node.prev = prev;
        prev.next = node;
        prev = node;

        node = new Node();
        node.val = 6;
        node.prev = prev;
        prev.next = node;
        prev = node;

        traverse(head);

        System.out.println("Hi there!");
    }

    @Test
    void test2() {
        val head = new Node();
        head.val = 1;
        var node = new Node();
        node.val = 2;
        head.next = node;
        node.prev = head;
        var prev = node;

        // Adding children Level 1
        var child = new Node();
        child.val = 7;
        node.child = child;
        var prevChild = child;
        child = new Node();
        child.val = 8;
        child.prev = prevChild;
        prevChild.next = child;
        prevChild = child;

        traverse(head);
        System.out.println("FlattenMultilevelDoublyLinkedList.test2");
    }
}
