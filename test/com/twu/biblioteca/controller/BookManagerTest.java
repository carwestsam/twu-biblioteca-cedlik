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
        ArrayList<HashMap<String, String>> mapped = bookManager.getMappedObjects(bookManager.getBookArray());
        assertThat(mapped.get(0).get("title"), is("a"));
        assertEquals(mapped.get(1).get("author"), "bb");
        assertEquals(mapped.get(1).get("year"), "2");
    }

    @Test
    public void should_checkout_a_book() throws Exception {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("a","A", 11));
        bookManager.add(new Book("b", "B", 12));
        bookManager.add(new Book("c", "C", 13));
        bookManager.add(new Book("d", "D", 14));

        assertThat(bookManager.getCount(), is(4));
        assertThat(bookManager.getBorrowedCount(), is(0));

        bookManager.checkoutById(1);

        assertThat(bookManager.getCount(), is(3));
        assertThat(bookManager.getBorrowedCount(), is(1));

        int r1 = bookManager.checkoutById(3);
        int r2 = bookManager.checkoutById(2);
        assertEquals(r1, 0);
        assertEquals(r2, 1);
        assertThat(bookManager.getCount(), is(2));
        assertThat(bookManager.getBorrowedCount(), is(2));
        ArrayList<HashMap<String, String>> mapped = bookManager.getMappedObjects(bookManager.getBookArray());
        assertEquals(mapped.get(0).get("title"), "a");
        assertEquals(mapped.get(1).get("title"), "c");
    }

    @Test
    public void should_return_a_book() throws Exception {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("a","A", 11));
        bookManager.add(new Book("b", "B", 12));
        bookManager.add(new Book("c", "C", 13));
        bookManager.add(new Book("d", "D", 14));

        bookManager.checkoutById(2);
        bookManager.checkoutById(0);

        ArrayList<Book> checkArray = bookManager.getCheckArray();
        assertThat(checkArray.size(), is(2));
        assertEquals(checkArray.get(0).getTitle(), "c");
        assertEquals(checkArray.get(1).getTitle(), "a");

        bookManager.handBackById(0);
        assertThat(bookManager.getCount(), is(3));
        assertThat(bookManager.getBorrowedCount(), is(1));
        assertThat(bookManager.getMappedObjects(bookManager.getBookArray()).get(2).get("title"), is("c"));
    }
}
