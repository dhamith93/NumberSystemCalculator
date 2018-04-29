package me.dhamith.numbersystemcalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by dhamith on 4/29/18.
 */

public class OctalTest {
    private StringParser parser;
    private ExpressionTree tree;

    @Test
    public void addition() throws Exception {
        parser = new StringParser("12+12", "oct");
        tree = parser.getTree();
        assertEquals("24", tree.getResult());
    }

    @Test
    public void subtraction() throws Exception {
        parser = new StringParser("12-12", "oct");
        tree = parser.getTree();
        assertEquals("0", tree.getResult());
    }

    @Test
    public void multiplication() throws Exception {
        parser = new StringParser("12*12", "oct");
        tree = parser.getTree();
        assertEquals("144", tree.getResult());
    }

    @Test
    public void division() throws Exception {
        parser = new StringParser("12/12", "oct");
        tree = parser.getTree();
        assertEquals("1", tree.getResult());
    }

    @Test
    public void combination() throws Exception {
        parser = new StringParser("16/2+5*12-2", "oct");
        tree = parser.getTree();
        assertEquals("67", tree.getResult());
    }

    @Test
    public void complex() throws Exception {
        parser = new StringParser("2*5*3+13*17/24+24/24+50+62*5", "oct");
        tree = parser.getTree();
        assertEquals("511", tree.getResult());
    }
}
