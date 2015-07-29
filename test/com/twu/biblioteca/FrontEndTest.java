package com.twu.biblioteca;

import com.twu.biblioteca.controller.BookManager;
import com.twu.biblioteca.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class FrontEndTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private String menuContent;
    private String invalidContent;
    private String quitContent;

    @Before
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        menuContent = "[1]list all the books\n[0]quit\nPlease input the instruction number:\n";
        invalidContent = "Select a valid option!\n";
        quitContent = "Thanks for using\n";
    }

    @After
    public void cleanUpStreams(){
        System.setOut(null);
        System.setErr(null);
        outContent.reset();
    }
    @Test
    public void should_Welcome() throws Exception {
        FrontEnd front = new FrontEnd();

        front.displayWelcome();
        assertEquals("Welcome to Biblioteca\n", outContent.toString());
    }

    @Test
    public void should_list_books() throws Exception {

        BookManager bookManager = new BookManager();
        bookManager.add(new Book("C++"));
        bookManager.add(new Book("python"));
        FrontEnd front = new FrontEnd(bookManager);

        front.displayWelcome();
        assertEquals("Welcome to Biblioteca\n", outContent.toString());
        outContent.reset();

        front.listBooks();
        assertEquals(outContent.toString(), "1\tC++\n2\tpython\n");
    }

    @Test
    public void should_get_detailed_books() throws Exception {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("CPP", "Stanley", 1984));
        bookManager.add(new Book("python", "Monty", 1996));
        FrontEnd frontEnd = new FrontEnd(bookManager);

        frontEnd.listDetailedBooks();
        assertEquals(outContent.toString(), "idx\ttitle\tauthor\tyear\n1\tCPP\tStanley\t1984\n2\tpython\tMonty\t1996\n");
        outContent.reset();

        bookManager.add(new Book("JAVA", "Marktin", 1998));
        frontEnd.listDetailedBooks();
        assertEquals(outContent.toString(), "idx\ttitle\tauthor\tyear\n1\tCPP\tStanley\t1984\n2\tpython\tMonty\t1996\n3\tJAVA\tMarktin\t1998\n");
    }

    @Test
    public void should_print_menu_inform() throws Exception {
        FrontEnd frontEnd = new FrontEnd(new BookManager());
        ByteArrayInputStream input = new ByteArrayInputStream("0\n".getBytes());
        System.setIn(input);
        frontEnd.displayMenu();
        assertEquals(outContent.toString(), menuContent + quitContent);
    }

    @Test
    public void should_get_menu_input_and_print_menu() throws Exception {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("a", "aa", 1990));
        FrontEnd frontEnd = new FrontEnd(bookManager);

        ByteArrayInputStream input = new ByteArrayInputStream("1\n0\n".getBytes());
        System.setIn(input);
        frontEnd.displayMenu();
        assertEquals(outContent.toString(), menuContent + "idx\ttitle\tauthor\tyear\n" +
                "1\ta\taa\t1990\n" + menuContent + quitContent);
        System.setIn(null);
    }

    @Test
    public void should_warrning_invalid_instruction() throws Exception {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("a", "aa", 1990));
        FrontEnd frontEnd = new FrontEnd(bookManager);

        ByteArrayInputStream input = new ByteArrayInputStream("100\n0\n".getBytes());
        System.setIn(input);
        frontEnd.displayMenu();
        assertEquals(outContent.toString(), menuContent + invalidContent + menuContent + quitContent);
        System.setIn(null);
    }

    @Test
    public void should_loop_the_menu_and_quit() throws Exception {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("a", "aa", 1990));
        FrontEnd frontEnd = new FrontEnd(bookManager);

        ByteArrayInputStream input = new ByteArrayInputStream("1\n7\n0\n".getBytes());
        System.setIn(input);
        frontEnd.displayMenu();

        assertEquals(outContent.toString(), menuContent +
                "idx\ttitle\tauthor\tyear\n" +
                "1\ta\taa\t1990\n" + menuContent + invalidContent + menuContent + quitContent);
    }
}