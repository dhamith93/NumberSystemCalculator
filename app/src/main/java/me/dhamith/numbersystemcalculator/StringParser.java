package me.dhamith.numbersystemcalculator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dhamith on 2/14/18.
 */

class StringParser {
    private final String input;
    private final ExpressionTree tree;
    private final ArrayList<Character> digits = new ArrayList<>();
    private final ArrayList<Character> ops = new ArrayList<>();
    private final ArrayList<Character> priorityOps = new ArrayList<>();
    private final ArrayList<String> tokens = new ArrayList<>();

    StringParser(String input, String type) {
        this.input = input.toUpperCase();
        tree = new ExpressionTree(type);
        Character[] digits = new Character[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        Character[] ops = new Character[]{'+','-','*','/'};
        Character[] priorityOps = new Character[]{'*','/'};
        Collections.addAll(this.digits, digits);
        Collections.addAll(this.ops, ops);
        Collections.addAll(this.priorityOps, priorityOps);
    }

    private void tokenize() {
        StringBuilder operand = new StringBuilder();
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (ops.contains(c)) {
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

    /**
     * Rearranges the tokens so * and / gets priority.
     */
    private void rearrange() {
        String[] tempTokens = tokens.toArray(new String[0]);
        int length = tokens.size();
        for (int i = 0; i < length; i += 1) {
            if (!ops.contains(tempTokens[i].charAt(0)))
                continue;
            if ((i + 1) < length && priorityOps.contains(tempTokens[i].charAt(0)) && !ops.contains(tempTokens[i - 1].charAt(0))) {
                String temp = tempTokens[i - 1];
                tempTokens[i - 1] = tempTokens[i];
                tempTokens[i] = temp;
                continue;
            }
            if ((i + 2) < length && priorityOps.contains(tempTokens[i + 2].charAt(0))) {
                String temp = tempTokens[i + 1];
                tempTokens[i + 1] = tempTokens[i + 2];
                tempTokens[i + 2] = temp;
            }
        }
        tokens.clear();
        Collections.addAll(tokens, tempTokens);
    }

    ExpressionTree getTree() {
        tokenize();
        if (tokens.size() > 3)
            rearrange();
        for (String s : tokens) {
            tree.insert(s, ops.contains(s.charAt(0)));
        }
        return tree;
    }
}
