package me.dhamith.numbersystemcalculator;

/**
 * Created by dhamith on 2/14/18.
 */

class Node {
    public final String data;
    final boolean op;
    String result;
    Node left;
    Node right;

    Node(String data, boolean op) {
        this.data = data;
        this.op = op;
        this.result = "";
        this.left = null;
        this.right = null;
    }
}
