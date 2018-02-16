package me.dhamith.numbersystemcalculator;

import java.util.ArrayList;

/**
 * Created by dhamith on 2/14/18.
 */

class StringParser {
    static List getList(String x, String type) {
        List opList = new List(type);
        ArrayList<Character> digits = new ArrayList<>();
        String str = "0123456789ABCDEFabcdef";
        for (int i = 0; i < 22; i++) {
            digits.add(str.charAt(i));
        }
        char a, op = ' ';
        StringBuilder oprnd1 = new StringBuilder();
        StringBuilder oprnd2 = new StringBuilder();
        boolean oprand1Done = false;
        boolean oprand2Done;
        int length = x.length() - 1;
        for (int i = 0; i < x.length(); i++) {
            a = x.charAt(i);
            if (digits.contains(a)) {
                if (!oprand1Done) {
                    oprnd1.append(a);
                } else {
                    oprnd2.append(a);
                }
                if (i == length) {
                    opList.insert(op, oprnd1.toString(), oprnd2.toString());
                }
            } else if (a == '+' || a == '-' || a == '*' || a == '/') {
                oprand2Done = (oprand1Done);
                oprand1Done = (!oprand1Done);
                op = (oprand1Done) ? a : op;
                if (oprand2Done) {
                    oprand1Done = true;
                    opList.insert(op, oprnd1.toString(), oprnd2.toString());
                    op = a;
                    oprnd1 = new StringBuilder(oprnd2);
                    oprnd2.setLength(0);
                }
            } else {
                throw new RuntimeException("Unknown values!");
            }
        }
        return opList;
    }
}
