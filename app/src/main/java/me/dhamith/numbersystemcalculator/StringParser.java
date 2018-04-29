package me.dhamith.numbersystemcalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by dhamith on 2/14/18.
 */

class StringParser {
    private final String input;
    private final String type;
    private final ArrayList<Character> digits = new ArrayList<>();
    private ArrayList<String> tokens = new ArrayList<>();

    StringParser(String input, String type) {
        this.input = input.toUpperCase();
        this.type = type;
        Character[] digits = new Character[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        Collections.addAll(this.digits, digits);
    }

    private void tokenize() {
        StringBuilder operand = new StringBuilder();
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (op(c)) {
                tokens.add(operand.toString());
                tokens.add(String.valueOf(c));
                operand = new StringBuilder();
                continue;
            }
            if (digits.contains(input.charAt(i))) {
                operand.append(String.valueOf(c));
                if (i == (length - 1))
                    tokens.add(operand.toString());
                continue;
            }
            throw new RuntimeException("Unknown values!");
        }
    }

    private boolean op(String str) {
        return (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/"));
    }

    private boolean op(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    private void convertToPostfix() {
        tokenize();
        Stack<String> operations = new Stack<>();
        ArrayList<String> output = new ArrayList<>();
        HashMap<String, Integer> order = new HashMap<>();
        order.put("+", 1);
        order.put("-", 1);
        order.put("*", 2);
        order.put("/", 2);
        for (String token : tokens) {
            if (!op(token)) {
                output.add(token);
                continue;
            }
            if (operations.empty()) {
                operations.push(token);
                continue;
            }
            while (!operations.empty() && order.get(operations.peek()) >= order.get(token))
                output.add(operations.pop());
            operations.push(token);
        }
        while (!operations.empty())
            output.add(operations.pop());
        tokens.clear();
        tokens = output;
    }

    private ExpressionTree makeTree() {
        Stack<ExpressionTree> trees = new Stack<>();
        for (String token: tokens) {
            if (!op(token)) {
                ExpressionTree t = new ExpressionTree(type);
                t.insert(token, false);
                trees.push(t);
                continue;
            }
            ExpressionTree tempTree = new ExpressionTree(type);
            tempTree.insert(token, true);
            ExpressionTree t = new ExpressionTree(type);
            if (!trees.peek().root.op) {
                t = trees.pop();
            }
            tempTree.insert(trees.pop().root);
            if (t.root != null)
                tempTree.insert(t.root);
            else
                tempTree.insert(trees.pop().root);
            trees.push(tempTree);
        }
        return trees.pop();
    }

    ExpressionTree getTree() {
        convertToPostfix();
        return makeTree();
    }
}
