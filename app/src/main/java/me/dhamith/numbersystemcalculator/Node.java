package me.dhamith.numbersystemcalculator;

/**
 * Created by dhamith on 2/14/18.
 */

class Node {
    final String left;
    final String right;
    final char op;
    String result;
    boolean done;
    Node next;

    Node (char op, String left, String right) {
        this.op = op;
        this.left = left;
        this.right = right;
        this.done = false;
        this.next = null;
    }

    @Override
    public String toString() {
        return left + " " + op + " " + right;
    }
}
