package me.dhamith.numbersystemcalculator;

/**
 * Created by dhamith on 2/14/18.
 */

public class HexadecimalCalculator implements Calculator {
    @Override
    public String getResult(String op, String a, String b) throws DivideByZeroException {
        switch (op) {
            case "+":
                return add(a, b);

            case "-":
                return subtract(a, b);

            case "*":
                return multiply(a, b);

            case "/":
                if (Long.parseLong(b, 16) == 0)
                    throw new DivideByZeroException();
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
        return Long.toString((n1 / n2), 16);
    }
}
