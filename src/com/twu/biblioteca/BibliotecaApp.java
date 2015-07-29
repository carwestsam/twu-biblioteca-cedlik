package com.twu.biblioteca;

import com.twu.biblioteca.controller.BookManager;
import com.twu.biblioteca.model.Book;

public class BibliotecaApp {

    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("Primer", "Stanley", 1984));
        bookManager.add(new Book("python", "Monty", 1996));
        bookManager.add(new Book("JAVA", "Marktin", 1998));
        FrontEnd frontEnd = new FrontEnd(bookManager);
        frontEnd.start();
        frontEnd.listDetailedBooks();
    }
}
