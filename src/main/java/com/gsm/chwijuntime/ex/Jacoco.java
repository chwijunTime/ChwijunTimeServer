package com.gsm.chwijuntime.ex;
/**
 * Jacoco Class
 */
public class Jacoco {

    /**
     * sum num1, num2
     *
     * @param num1 num1
     * @param num2 num2
     * @return result
     */
    public int sum(int num1, int num2) {
        return num1 + num2;
    }

    /**
     * subtract num2 from num1 .
     *
     * @param num1 num1
     * @param num2 num2
     * @return result
     */
    public int subtract(int num1, int num2) {
        return num1 - num2;
    }

    /**
     * null check
     *
     * @param obj obj
     * @return result
     */
    public boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }
}