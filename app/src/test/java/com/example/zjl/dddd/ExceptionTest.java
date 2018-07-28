package com.example.zjl.dddd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/7/23 0023.
 */
public class ExceptionTest {
    private Exception exception;
    @Before
    public void setUp() throws Exception {
        exception = new Exception();
    }

    @Test
    public void div() throws Exception {
        assertEquals(1, exception.div(5, 0), 0);
    }

}