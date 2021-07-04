package com.gsm.chwijuntime;

import java.util.Objects;

public final class PositiveNumber {
    private final long value;

    public PositiveNumber(long value) {
        validate(value);
        this.value = value;
    }

    private void validate(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException(String.format("%d 는 양수가 아닙니다.", value));
        }
    }

    public PositiveNumber add(PositiveNumber positiveNumber) {
        return new PositiveNumber(value + positiveNumber.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositiveNumber that = (PositiveNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}