package com.twu.biblioteca;

import com.twu.biblioteca.controller.ItemManager;
import com.twu.biblioteca.controller.UserManager;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.service.Library;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
//        BookManager bookManager = new BookManager();
//        bookManager.add(new Book("Primer", "Stanley", 1984));
//        bookManager.add(new Book("python", "Monty", 1996));
//        bookManager.add(new Book("JAVA", "Marktin", 1998));
//        FrontEnd frontEnd = new FrontEnd(bookManager);
//        frontEnd.displayWelcome();
//        frontEnd.displayMenu();

        ItemManager itemManager = new ItemManager();
        itemManager.add(new Book ("Primer", "Stanley", 1984));
        itemManager.add(new Book ("Python", "Monty", 1996));
        itemManager.add(new Movie("Matrix",1993, "Woco", 9));
        itemManager.add(new Book ("JAVA", "Markin", 1998));
        itemManager.add(new Movie("Potter", 2001, "Jing", 7));
        itemManager.add(new Movie("Hugo", 2013, "Hugo", -1));
        itemManager.add(new Book ("Timer", "Hokin", 1997));

        FrontEnd2 front = new FrontEnd2(new Library(new UserManager(), new ItemManager()), new Scanner(System.in), new User("user1", "123456", 1, "123@g.com", "13912345678"));

        front.display(front.welcome());
        front.displayMenu();

    }
}
