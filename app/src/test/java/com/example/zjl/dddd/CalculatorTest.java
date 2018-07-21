package com.example.zjl.dddd;

import junit.extensions.TestSetup;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 在该类中，setUp方法用来实例化我们的需要测试类，然后，在加减乘除方法调用assertEquals()方法，来计算测试结果，
 * 第一个参数，需要填入正确结果，第二个参数为我们的方法调用，第三个参数为允许误差值。
 * 这里，我们给第一个的参数正确，第二个计算结果错误 第三个计算结果在误差值内，第四个结果没在误差值之外
 */
public class CalculatorTest {
    private Calculator mCalculator;
    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @Test
    public void sum() throws Exception {
        assertEquals(6d, mCalculator.sum(1d, 5d), 0);
    }

    @Test
    public void substract() throws Exception {
        assertEquals(2d, mCalculator.substract(5d, 4d), 0);
    }

    @Test
    public void divide() throws Exception {
        assertEquals(3d, mCalculator.divide(20d, 5d), 1);
    }

    @Test
    public void multiply() throws Exception {
        assertEquals(12d, mCalculator.multiply(2d, 5d), 1);
    }


}