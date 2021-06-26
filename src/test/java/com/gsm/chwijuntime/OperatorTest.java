package com.gsm.chwijuntime;

import com.gsm.chwijuntime.ex.Operator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperatorTest {
    private Operator operator = new Operator();

    @Test
    public void add() {
        int actual = operator.add(2, 6);
        int expected = 8;

        assertEquals(expected, actual);
    }
}