package me.dhamith.numbersystemcalculator;

/**
 * Created by dhamith on 2/14/18.
 */

interface Calculator {
    String getResult(String op, String a, String b) throws DivideByZeroException;
    String add(String a, String b);
    String subtract(String a, String b);
    String multiply(String a, String b);
    String divide(String a, String b);
//    String inverse(String a, int length);
}
