package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.Book;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

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

    @Test
    public void should_return_mapped_bookArray() throws Exception {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("a", "aa", 1));
        bookManager.add(new Book("b", "bb", 2));
        ArrayList<HashMap<String, String>> mapped = bookManager.getMappedObjects();
        assertThat(mapped.get(0).get("title"), is("a"));
        assertEquals(mapped.get(1).get("author"), "bb");
        assertEquals(mapped.get(1).get("year"), "2");
    }
}
