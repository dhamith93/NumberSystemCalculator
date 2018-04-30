package me.dhamith.numbersystemcalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by dhamith on 4/29/18.
 */

public class BinaryTest {
    private StringParser parser;
    private ExpressionTree tree;

    @Test
    public void addition() throws Exception {
        parser = new StringParser("1010+1010", "bin");
        tree = parser.getTree();
        assertEquals("10100", tree.getResult());
    }

    @Test
    public void subtraction() throws Exception {
        parser = new StringParser("1010-1010", "bin");
        tree = parser.getTree();
        assertEquals("0", tree.getResult());
    }

    @Test
    public void multiplication() throws Exception {
        parser = new StringParser("1010×1010", "bin");
        tree = parser.getTree();
        assertEquals("1100100", tree.getResult());
    }

    @Test
    public void division() throws Exception {
        parser = new StringParser("1010÷1010", "bin");
        tree = parser.getTree();
        assertEquals("1", tree.getResult());
    }

    @Test
    public void combination() throws Exception {
        parser = new StringParser("1110÷10+101×1010-10", "bin");
        tree = parser.getTree();
        assertEquals("110111", tree.getResult());
    }

    @Test
    public void complex() throws Exception {
        parser = new StringParser("10×101×11+1011×1111÷10100+10100÷10100+101000+110010×101", "bin");
        tree = parser.getTree();
        assertEquals("101001001", tree.getResult());
    }
}
