package com.gsm.chwijuntime;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositiveNumberTest {

    @Test
    public void notPositiveValueThrowException() {
        long notPositive = 0;

        assertThatThrownBy(() -> new PositiveNumber(notPositive))
                .isInstanceOf(IllegalArgumentException.class);
    }
}