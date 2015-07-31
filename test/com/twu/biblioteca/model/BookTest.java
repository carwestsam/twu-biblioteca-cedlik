package com.twu.biblioteca.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BookTest {
    @Before
    public void init() throws Exception{
        Item.resetId();
    }

    @Test
    public void should_create_a_book() throws Exception {
        Book book = new Book("C++ Primer");
        assertEquals(book.getTitle(), "C++ Primer");
    }

    @Test
    public void should_create_a_book_with_details() throws Exception {
        Book book = new Book("C++ Primer", "Stanley", 1984);
        assertEquals(book.getTitle(), "C++ Primer");
        assertEquals(book.getAuthor(), "Stanley");
        assertEquals(book.getYear(), 1984);
        assertEquals(Book.TYPES.Book, book.getType());
        assertThat(book.getId(), is(1));
    }

    @Test
    public void should_convert_object_to_map_dict() throws Exception {
        Book book = new Book("C++ Primer", "Stanley", 1984);
        HashMap<String, String> dict = book.getHashMap();
        assertEquals(dict.get("title"), "C++ Primer");
        assertEquals(dict.get("author"), "Stanley");
        assertEquals(dict.get("year"), "1984");
    }
}