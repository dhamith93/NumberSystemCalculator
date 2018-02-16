package me.dhamith.numbersystemcalculator;

/**
 * Created by dhamith on 2/14/18.
 */

public class BinaryCalculator implements Calculator {
    @Override
    public String getResult(char op, String a, String b) {
        switch (op) {
            case '+':
                return add(a, b);

            case '-':
                return subtract(a, b);

            case '*':
                return multiply(a, b);

            case '/':
                return divide(a, b);

            default:
                throw new RuntimeException("Unknown Op");
        }
    }

    @Override
    public String add(String a, String b) {
        long n1 = Long.parseLong(a, 2);
        long n2 = Long.parseLong(b, 2);
        return Long.toString((n1 + n2), 2);
    }

    @Override
    public String subtract(String a, String b) {
        long n1 = Long.parseLong(a, 2);
        long n2 = Long.parseLong(b, 2);
        return Long.toString((n1 - n2), 2);
    }

    @Override
    public String multiply(String a, String b) {
        long n1 = Long.parseLong(a, 2);
        long n2 = Long.parseLong(b, 2);
        return Long.toString((n1 * n2), 2);
    }

    @Override
    public String divide(String a, String b) {
        long n1 = Long.parseLong(a, 2);
        long n2 = Long.parseLong(b, 2);
        double n3;
        if (n2 > 0) {
            n3 = (double)n1 / (double)n2;
            return Long.toString((long)n3, 2);
        }
        throw new RuntimeException();
    }

//    @Override
//    public String inverse(String a, int length) {
//        StringBuilder output = new StringBuilder("");
//        char a1;
//        for (int i = 0; i < length; i++) {
//            if (i < a.length()) {
//                a1 = (a.charAt(i) == '1') ? '0' : '1';
//                output.append(a1);
//            } else {
//                output.insert(0, "1");
//            }
//        }
//        return output.toString();
//    }
}
