package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ExampleTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams(){
        System.setOut(null);
        System.setErr(null);
        outContent.reset();
    }

    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void should_Welcome() throws Exception {
        FrontEnd front = new FrontEnd();

        front.start();
        assertEquals("Welcome to Biblioteca\n", outContent.toString());

    }
}
