package com.twu.biblioteca;

import com.twu.biblioteca.controller.BookManager;
import com.twu.biblioteca.controller.ItemManager;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Item;
import com.twu.biblioteca.model.Movie;
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
    private String checkoutContent;
    private String checkoutSuccessContent;
    private String checkoutFailedContent;
    private String returnContent;
    private String returnSuccessContent;
    private String returnFailedContent;
    private String welcomeContent;

    @Before
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        Item.resetId();
        welcomeContent = "Welcome to Biblioteca\n";
        menuContent = "\n---\n\n[1]list all the books\n[2]Checkout book\n[3]Return book\n[4]List all the borrowed books\n[0]quit\nPlease input the instruction number:\n";
        invalidContent = "Select a valid option!\n";
        quitContent = "Thanks for using\n";
        checkoutContent = "Choose the book number to checkout(0 to quit):\n";
        checkoutSuccessContent = "Thank you! Enjoy the book\n";
        checkoutFailedContent = "That book is not available.\n";
        returnContent = "Choose the book number to return(0 to quit):\n";
        returnSuccessContent = "Thank you for returning the book.\n";
        returnFailedContent = "That is not a valid book to return.\n";
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
        assertEquals(welcomeContent, outContent.toString());
    }

    @Test
    public void should_Welcome2() throws Exception {
        FrontEnd2 frontEnd2 = new FrontEnd2(new ItemManager());

        frontEnd2.display(frontEnd2.displayWelcome());
        assertEquals(welcomeContent, outContent.toString());
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
    public void should_list_books2() throws Exception {
        ItemManager itemManager = new ItemManager();
        itemManager.add(new Book("CPP", "Stanley", 1984));
        itemManager.add(new Book("python", "Monty", 1996));
        FrontEnd2 frontEnd2 = new FrontEnd2(itemManager);

        frontEnd2.displayBooks();
        assertEquals(outContent.toString(), "id\ttitle\tauthor\tyear\t\n1\tCPP\tStanley\t1984\t\n2\tpython\tMonty\t1996\t\n");
    }

    @Test
    public void should_print_menu_inform() throws Exception {
        FrontEnd frontEnd = new FrontEnd(new BookManager());
        ByteArrayInputStream input = new ByteArrayInputStream("0\n".getBytes());
        System.setIn(input);
        frontEnd.displayMenu();
        assertEquals(outContent.toString(), menuContent + quitContent);
    }

    private void consoleInput(String instruction){
        ByteArrayInputStream input = new ByteArrayInputStream(instruction.getBytes());
        System.setIn(input);
    }

    @Test
    public void should_print_menu_inform2() throws Exception {
        FrontEnd2 frontEnd2 = new FrontEnd2(new ItemManager());
        consoleInput("0\n");
        frontEnd2.displayMenu();
        assertEquals(outContent.toString(), FrontEnd2.menu() + FrontEnd2.quit());
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
    public void should_display_Book_List() throws Exception {
        ItemManager itemManager = new ItemManager();
        addItems(itemManager);
        FrontEnd2 frontEnd2 = new FrontEnd2(itemManager);
        consoleInput("1\n0\n");
        frontEnd2.displayMenu();
        assertEquals(outContent.toString(), FrontEnd2.menu() + frontEnd2.Books() + FrontEnd2.menu() + FrontEnd2.quit());
    }

    private void addItems(ItemManager itemManager) {
        itemManager.add(new Book("a", "aa", 1992));
        itemManager.add(new Book("b", "bb", 1993));
        itemManager.add(new Movie("A", 1931, "AA", 5));
        itemManager.add(new Book("c", "cc", 1994));
        itemManager.add(new Movie("B", 1932, "BB", -1));
        itemManager.add(new Movie("C", 1933, "CC", 2));
        itemManager.add(new Book("d", "dd", 1995));
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
    public void should_display_invalid_instruction() throws Exception {
        ItemManager itemManager = new ItemManager();
        addItems(itemManager);
        FrontEnd2 frontEnd2 = new FrontEnd2(itemManager);
        consoleInput("100\n0\n");
        frontEnd2.displayMenu();
        assertEquals(outContent.toString(), FrontEnd2.menu() + FrontEnd2.invalid() + FrontEnd2.menu() + FrontEnd2.quit());
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

    @Test
    public void should_loop_the_menu() throws Exception {
        ItemManager itemManager = new ItemManager();
        addItems(itemManager);
        FrontEnd2 frontEnd2 = new FrontEnd2(itemManager);

        consoleInput("1\n7\n0\n");

        frontEnd2.displayMenu();

        assertEquals(outContent.toString(),
                FrontEnd2.menu() + frontEnd2.Books() +
                FrontEnd2.menu() + FrontEnd2.invalid() +
                FrontEnd2.menu() + FrontEnd2.quit());
    }

    @Test
    public void check_out_a_book() throws Exception {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("a", "1", 1991));
        bookManager.add(new Book("b", "2", 1992));
        bookManager.add(new Book("c", "3", 1993));

        FrontEnd frontEnd = new FrontEnd(bookManager);

        String backupList = frontEnd.listDetailedBookString();

        ByteArrayInputStream input = new ByteArrayInputStream("1\n2\n1\n0\n".getBytes());
        System.setIn(input);

        frontEnd.displayMenu();
        assertEquals(outContent.toString(), menuContent + backupList + menuContent + checkoutContent + checkoutSuccessContent + menuContent + quitContent);
    }

    @Test
    public void should_check_out_a_book_fail() throws Exception {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("a", "1", 1991));
        bookManager.add(new Book("b", "2", 1992));
        bookManager.add(new Book("c", "3", 1993));

        FrontEnd frontEnd = new FrontEnd(bookManager);

        String backupList = frontEnd.listDetailedBookString();

        ByteArrayInputStream input = new ByteArrayInputStream("1\n2\n4\n1\n0\n".getBytes());
        System.setIn(input);

        frontEnd.displayMenu();
        assertEquals(outContent.toString(), menuContent + backupList +
                menuContent + checkoutContent + checkoutFailedContent + backupList +
                checkoutContent + checkoutSuccessContent +
                menuContent + quitContent);

    }

    @Test
    public void should_check_out_book() throws Exception {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("a", "1", 1991));
        bookManager.add(new Book("b", "2", 1992));
        bookManager.add(new Book("c", "3", 1993));
        FrontEnd frontEnd = new FrontEnd(bookManager);
        frontEnd.checkoutBook(0);
        assertEquals("idx\ttitle\tauthor\tyear\n1\tb\t2\t1992\n2\tc\t3\t1993\n", frontEnd.listDetailedBookString() );
        frontEnd.checkoutBook(1);
        assertEquals("idx\ttitle\tauthor\tyear\n1\tb\t2\t1992\n", frontEnd.listDetailedBookString() );
    }

    @Test
    public void should_return_a_book() throws Exception {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("a", "1", 1991));
        bookManager.add(new Book("b", "2", 1992));
        bookManager.add(new Book("c", "3", 1993));

        FrontEnd frontEnd = new FrontEnd(bookManager);
        String backupList = frontEnd.listDetailedBookString();
        ByteArrayInputStream input = new ByteArrayInputStream("1\n2\n1\n2\n1\n3\n3\n2\n3\n1\n1\n0\n".getBytes());
        System.setIn(input);

        frontEnd.displayMenu();
        assertEquals(outContent.toString(), menuContent + backupList +
                menuContent + checkoutContent + checkoutSuccessContent +
                menuContent + checkoutContent + checkoutSuccessContent +
                menuContent + returnContent + returnFailedContent + "idx\ttitle\tauthor\tyear\n1\ta\t1\t1991\n2\tb\t2\t1992\n" +
                returnContent + returnSuccessContent +
                menuContent + returnContent + returnSuccessContent +
                menuContent + "idx\ttitle\tauthor\tyear\n1\tc\t3\t1993\n2\tb\t2\t1992\n3\ta\t1\t1991\n" +
                menuContent + quitContent);
    }

    @Test
    public void should_list_borrowed_books() throws Exception {
        BookManager bookManager = new BookManager();
        bookManager.add(new Book("a", "1", 1991));
        bookManager.add(new Book("b", "2", 1992));
        bookManager.add(new Book("c", "3", 1993));

        FrontEnd frontEnd = new FrontEnd(bookManager);
        String backupList = frontEnd.listDetailedBookString();
        ByteArrayInputStream input = new ByteArrayInputStream("1\n2\n2\n4\n0\n".getBytes());
        System.setIn(input);

        frontEnd.displayMenu();
        assertEquals(outContent.toString(), menuContent + backupList +
                menuContent + checkoutContent + checkoutSuccessContent +
                menuContent + "idx\ttitle\tauthor\tyear\n1\tb\t2\t1992\n" +
                menuContent + quitContent);
    }
}
