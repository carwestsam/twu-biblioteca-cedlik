package com.twu.biblioteca.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void should_create_a_book() throws Exception {
        Book book = new Book("C++ Primer");
        assertEquals(book.getTitle(), "C++ Primer");
    }
}