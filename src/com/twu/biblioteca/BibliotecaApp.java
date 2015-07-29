package com.twu.biblioteca;

import com.twu.biblioteca.controller.BookManager;
import com.twu.biblioteca.model.Book;

public class BibliotecaApp {

    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("C++"));
        bookManager.add(new Book("python"));
        FrontEnd frontEnd = new FrontEnd(bookManager);
        frontEnd.start();
        frontEnd.listBooks();
    }
}
