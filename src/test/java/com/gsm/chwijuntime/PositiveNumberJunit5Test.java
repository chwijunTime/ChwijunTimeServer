package com.gsm.chwijuntime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class PositiveNumberJunit5Test {

    @DisplayName("양수 생성")
    @Test
    void createTest() {
        long positive = 1;
        PositiveNumber positiveNumber = new PositiveNumber(positive);

        assertThat(positiveNumber).isEqualTo(new PositiveNumber(1));
    }

    @DisplayName("양수 덧셈")
    @Test
    void addTest() {
        //given
        PositiveNumber result = new PositiveNumber(2);

        PositiveNumber positiveNumber1 = new PositiveNumber(1);
        PositiveNumber positiveNumber2 = new PositiveNumber(1);

        //when
        PositiveNumber expect = positiveNumber1.add(positiveNumber2);


        //then
        assertThat(expect).isEqualTo(result);
    }
}