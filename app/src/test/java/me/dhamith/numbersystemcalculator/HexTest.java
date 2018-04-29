package me.dhamith.numbersystemcalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dhamith on 4/29/18.
 */

public class HexTest {
    private StringParser parser;
    private ExpressionTree tree;

    @Test
    public void addition() throws Exception {
        parser = new StringParser("A+A", "hex");
        tree = parser.getTree();
        assertEquals("14", tree.getResult());
    }

    @Test
    public void subtraction() throws Exception {
        parser = new StringParser("A-A", "hex");
        tree = parser.getTree();
        assertEquals("0", tree.getResult());
    }

    @Test
    public void multiplication() throws Exception {
        parser = new StringParser("A*A", "hex");
        tree = parser.getTree();
        assertEquals("64", tree.getResult());
    }

    @Test
    public void division() throws Exception {
        parser = new StringParser("A/A", "hex");
        tree = parser.getTree();
        assertEquals("1", tree.getResult());
    }

    @Test
    public void combination() throws Exception {
        parser = new StringParser("E/2+5*A-2", "hex");
        tree = parser.getTree();
        assertEquals("37", tree.getResult());
    }

    @Test
    public void complex() throws Exception {
        parser = new StringParser("2*5*3+B*F/14+14/14+28+32*5", "hex");
        tree = parser.getTree();
        assertEquals("149", tree.getResult());
    }
}
