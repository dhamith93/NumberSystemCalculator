package me.dhamith.numbersystemcalculator;

/**
 * Created by dhamith on 2/14/18.
 */

public class HexadecimalCalculator implements Calculator {
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
        long n1 = Long.parseLong(a, 16);
        long n2 = Long.parseLong(b, 16);
        return Long.toString((n1 + n2), 16);
    }

    @Override
    public String subtract(String a, String b) {
        long n1 = Long.parseLong(a, 16);
        long n2 = Long.parseLong(b, 16);
        return Long.toString((n1 - n2), 16);
    }

    @Override
    public String multiply(String a, String b) {
        long n1 = Long.parseLong(a, 16);
        long n2 = Long.parseLong(b, 16);
        return Long.toString((n1 * n2), 16);
    }

    @Override
    public String divide(String a, String b) {
        long n1 = Long.parseLong(a, 16);
        long n2 = Long.parseLong(b, 16);
        long res;
        if (n2 > 0) {
            res = n1 / n2;
        } else {
            throw new RuntimeException("Divide by 0");
        }
        return Long.toString(res, 16);
    }

//    @Override
//    public String inverse(String a, int length) {
//        return null;
//    }
}
