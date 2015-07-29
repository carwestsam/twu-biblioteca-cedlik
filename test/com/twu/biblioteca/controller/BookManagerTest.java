package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.Book;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BookManagerTest {
    @Test
    public void should_create_book_list() throws Exception {
        BookManager bookManager = new BookManager();
        assertThat(bookManager.getCount(), is(0));
        bookManager.add(new Book("C++"));
        bookManager.add(new Book("python"));
        assertThat(bookManager.getCount(), is(2));
        assertEquals(bookManager.get(0).getTitle(), "C++");
        assertEquals(bookManager.get(1).getTitle(), "python");
    }
}