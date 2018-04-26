package me.dhamith.numbersystemcalculator;

/**
 * Created by dhamith on 4/26/18.
 */

class DivideByZeroException extends Exception {
    DivideByZeroException() {
        super("Can't divide by 0");
    }
}
