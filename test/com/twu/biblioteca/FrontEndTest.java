package com.twu.biblioteca;

import com.twu.biblioteca.controller.ItemManager;
import com.twu.biblioteca.controller.UserManager;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Item;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.service.Library;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
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
    private ItemManager itemManager;

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
        itemManager = new ItemManager();
    }

    @After
    public void cleanUpStreams(){
        System.setOut(null);
        System.setErr(null);
        outContent.reset();
    }

//    @Ignore
//    @Test
//    public void should_Welcome() throws Exception {
//        FrontEnd front = new FrontEnd();
//        front.displayWelcome();
//        assertEquals(welcomeContent, outContent.toString());
//    }



//    @Ignore
//    @Test
//    public void should_list_books() throws Exception {
//
//        BookManager bookManager = new BookManager();
//        bookManager.add(new Book("C++"));
//        bookManager.add(new Book("python"));
//        FrontEnd front = new FrontEnd(bookManager);
//
//        front.displayWelcome();
//        assertEquals("Welcome to Biblioteca\n", outContent.toString());
//        outContent.reset();
//
//        front.listBooks();
//        assertEquals(outContent.toString(), "1\tC++\n2\tpython\n");
//    }


//    @Ignore
//    @Test
//    public void should_get_detailed_books() throws Exception {
//        BookManager bookManager = new BookManager();
//        bookManager.add(new Book("CPP", "Stanley", 1984));
//        bookManager.add(new Book("python", "Monty", 1996));
//        FrontEnd frontEnd = new FrontEnd(bookManager);
//
//        frontEnd.listDetailedBooks();
//        assertEquals(outContent.toString(), "idx\ttitle\tauthor\tyear\n1\tCPP\tStanley\t1984\n2\tpython\tMonty\t1996\n");
//        outContent.reset();
//
//        bookManager.add(new Book("JAVA", "Marktin", 1998));
//        frontEnd.listDetailedBooks();
//        assertEquals(outContent.toString(), "idx\ttitle\tauthor\tyear\n1\tCPP\tStanley\t1984\n2\tpython\tMonty\t1996\n3\tJAVA\tMarktin\t1998\n");
//    }
//
//
//    @Ignore
//    @Test
//    public void should_print_menu_inform() throws Exception {
//        FrontEnd frontEnd = new FrontEnd(new BookManager());
//        ByteArrayInputStream input = new ByteArrayInputStream("0\n".getBytes());
//        System.setIn(input);
//        frontEnd.displayMenu();
//        assertEquals(outContent.toString(), menuContent + quitContent);
//    }
//    @Ignore
//    @Test
//    public void should_get_menu_input_and_print_menu() throws Exception {
//        BookManager bookManager = new BookManager();
//        bookManager.add(new Book("a", "aa", 1990));
//        FrontEnd frontEnd = new FrontEnd(bookManager);
//
//        ByteArrayInputStream input = new ByteArrayInputStream("1\n0\n".getBytes());
//        System.setIn(input);
//        frontEnd.displayMenu();
//        assertEquals(outContent.toString(), menuContent + "idx\ttitle\tauthor\tyear\n" +
//                "1\ta\taa\t1990\n" + menuContent + quitContent);
//        System.setIn(null);
//    }
//    @Ignore
//    @Test
//    public void should_warrning_invalid_instruction() throws Exception {
//        BookManager bookManager = new BookManager();
//        bookManager.add(new Book("a", "aa", 1990));
//        FrontEnd frontEnd = new FrontEnd(bookManager);
//
//        ByteArrayInputStream input = new ByteArrayInputStream("100\n0\n".getBytes());
//        System.setIn(input);
//        frontEnd.displayMenu();
//        assertEquals(outContent.toString(), menuContent + invalidContent + menuContent + quitContent);
//        System.setIn(null);
//    }
//@Ignore
//@Test
//public void should_loop_the_menu_and_quit() throws Exception {
//    BookManager bookManager = new BookManager();
//    bookManager.add(new Book("a", "aa", 1990));
//    FrontEnd frontEnd = new FrontEnd(bookManager);
//
//    ByteArrayInputStream input = new ByteArrayInputStream("1\n7\n0\n".getBytes());
//    System.setIn(input);
//    frontEnd.displayMenu();
//
//    assertEquals(outContent.toString(), menuContent +
//            "idx\ttitle\tauthor\tyear\n" +
//            "1\ta\taa\t1990\n" + menuContent + invalidContent + menuContent + quitContent);
//}
//    @Ignore
//    @Test
//    public void check_out_a_book() throws Exception {
//        BookManager bookManager = new BookManager();
//        bookManager.add(new Book("a", "1", 1991));
//        bookManager.add(new Book("b", "2", 1992));
//        bookManager.add(new Book("c", "3", 1993));
//
//        FrontEnd frontEnd = new FrontEnd(bookManager);
//
//        String backupList = frontEnd.listDetailedBookString();
//
//        ByteArrayInputStream input = new ByteArrayInputStream("1\n2\n1\n0\n".getBytes());
//        System.setIn(input);
//
//        frontEnd.displayMenu();
//        assertEquals(outContent.toString(), menuContent + backupList + menuContent + checkoutContent + checkoutSuccessContent + menuContent + quitContent);
//    }

//    @Ignore
//    @Test
//    public void should_check_out_a_book_fail() throws Exception {
//        BookManager bookManager = new BookManager();
//        bookManager.add(new Book("a", "1", 1991));
//        bookManager.add(new Book("b", "2", 1992));
//        bookManager.add(new Book("c", "3", 1993));
//
//        FrontEnd frontEnd = new FrontEnd(bookManager);
//
//        String backupList = frontEnd.listDetailedBookString();
//
//        ByteArrayInputStream input = new ByteArrayInputStream("1\n2\n4\n1\n0\n".getBytes());
//        System.setIn(input);
//
//        frontEnd.displayMenu();
//        assertEquals(outContent.toString(), menuContent + backupList +
//                menuContent + checkoutContent + checkoutFailedContent + backupList +
//                checkoutContent + checkoutSuccessContent +
//                menuContent + quitContent);
//
//    }

//    @Ignore
//    @Test
//    public void should_check_out_book() throws Exception {
//        BookManager bookManager = new BookManager();
//        bookManager.add(new Book("a", "1", 1991));
//        bookManager.add(new Book("b", "2", 1992));
//        bookManager.add(new Book("c", "3", 1993));
//        FrontEnd frontEnd = new FrontEnd(bookManager);
//        frontEnd.checkoutBook(0);
//        assertEquals("idx\ttitle\tauthor\tyear\n1\tb\t2\t1992\n2\tc\t3\t1993\n", frontEnd.listDetailedBookString() );
//        frontEnd.checkoutBook(1);
//        assertEquals("idx\ttitle\tauthor\tyear\n1\tb\t2\t1992\n", frontEnd.listDetailedBookString() );
//    }
//
//    @Ignore
//    @Test
//    public void should_return_a_book() throws Exception {
//        BookManager bookManager = new BookManager();
//        bookManager.add(new Book("a", "1", 1991));
//        bookManager.add(new Book("b", "2", 1992));
//        bookManager.add(new Book("c", "3", 1993));
//
//        FrontEnd frontEnd = new FrontEnd(bookManager);
//        String backupList = frontEnd.listDetailedBookString();
//        ByteArrayInputStream input = new ByteArrayInputStream("1\n2\n1\n2\n1\n3\n3\n2\n3\n1\n1\n0\n".getBytes());
//        System.setIn(input);
//
//        frontEnd.displayMenu();
//        assertEquals(outContent.toString(), menuContent + backupList +
//                menuContent + checkoutContent + checkoutSuccessContent +
//                menuContent + checkoutContent + checkoutSuccessContent +
//                menuContent + returnContent + returnFailedContent + "idx\ttitle\tauthor\tyear\n1\ta\t1\t1991\n2\tb\t2\t1992\n" +
//                returnContent + returnSuccessContent +
//                menuContent + returnContent + returnSuccessContent +
//                menuContent + "idx\ttitle\tauthor\tyear\n1\tc\t3\t1993\n2\tb\t2\t1992\n3\ta\t1\t1991\n" +
//                menuContent + quitContent);
//    }

//    @Ignore
//    @Test
//    public void should_list_borrowed_books() throws Exception {
//        BookManager bookManager = new BookManager();
//        bookManager.add(new Book("a", "1", 1991));
//        bookManager.add(new Book("b", "2", 1992));
//        bookManager.add(new Book("c", "3", 1993));
//
//        FrontEnd frontEnd = new FrontEnd(bookManager);
//        String backupList = frontEnd.listDetailedBookString();
//        ByteArrayInputStream input = new ByteArrayInputStream("1\n2\n2\n4\n0\n".getBytes());
//        System.setIn(input);
//
//        frontEnd.displayMenu();
//        assertEquals(outContent.toString(), menuContent + backupList +
//                menuContent + checkoutContent + checkoutSuccessContent +
//                menuContent + "idx\ttitle\tauthor\tyear\n1\tb\t2\t1992\n" +
//                menuContent + quitContent);
//    }
    private void consoleInput(String instruction){
        ByteArrayInputStream input = new ByteArrayInputStream(instruction.getBytes());
        System.setIn(input);
    }

    public static void addItems(ItemManager itemManager) {
        itemManager.add(new Book("a", "aa", 1992));
        itemManager.add(new Book("b", "bb", 1993));
        itemManager.add(new Movie("A", 1931, "AA", 5));
        itemManager.add(new Book("c", "cc", 1994));
        itemManager.add(new Movie("B", 1932, "BB", -1));
        itemManager.add(new Movie("C", 1933, "CC", 2));
        itemManager.add(new Book("d", "dd", 1995));
    }

    private FrontEnd2 initFront() {
        addItems(itemManager);
        return new FrontEnd2(new Library(new UserManager(), itemManager), new Scanner(System.in), new User("user1", "123456", 1, "123@g.com", "13912345678"));
    }


    @Test
    public void should_Welcome2() throws Exception {
        FrontEnd2 frontEnd2 = initFront();
        frontEnd2.display(frontEnd2.welcome());
        assertEquals(welcomeContent, outContent.toString());
    }

    @Test
    public void should_print_menu_inform2() throws Exception {
        consoleInput("0\n");
        FrontEnd2 frontEnd2 = initFront();
        frontEnd2.displayMenu();
        assertEquals(outContent.toString(), FrontEnd2.menu() + FrontEnd2.quit());
    }

    @Test
    public void should_list_books2() throws Exception {
        ItemManager itemManager = new ItemManager();
        itemManager.add(new Book("CPP", "Stanley", 1984));
        itemManager.add(new Book("python", "Monty", 1996));
        FrontEnd2 frontEnd2 = new FrontEnd2(new Library(new UserManager(), itemManager), new Scanner(System.in), new User("user1", "123456", 1, "123@g.com", "13912345678"));

        frontEnd2.displayBooks();
        assertEquals(outContent.toString(), "id\ttitle\tauthor\tyear\t\n1\tCPP\tStanley\t1984\t\n2\tpython\tMonty\t1996\t\n");
    }

    @Test
    public void should_display_Book_List() throws Exception {
        consoleInput("1\n0\n");
        FrontEnd2 frontEnd2 = initFront();
        frontEnd2.displayMenu();
        assertEquals(outContent.toString(), FrontEnd2.menu() + frontEnd2.Books() + FrontEnd2.menu() + FrontEnd2.quit());
    }

    @Test
    public void should_list_Available_Movies() throws Exception {
        consoleInput("5\n0\n");
        FrontEnd2 front = initFront();
        front.displayMenu();
        assertEquals(outContent.toString(), FrontEnd2.menu() + front.Movies() + FrontEnd2.menu() + FrontEnd2.quit());
        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Movie, 0).size(), is(3));
    }

    @Test
    public void should_display_invalid_instruction() throws Exception {
        consoleInput("100\n0\n");
        FrontEnd2 frontEnd2 = initFront();
        frontEnd2.displayMenu();
        assertEquals(outContent.toString(), FrontEnd2.menu() + FrontEnd2.invalid() + FrontEnd2.menu() + FrontEnd2.quit());
    }

    @Test
    public void should_loop_the_menu() throws Exception {
        consoleInput("1\n11\n0\n");

        FrontEnd2 frontEnd2 = initFront();

        frontEnd2.displayMenu();

        assertEquals(outContent.toString(),
                FrontEnd2.menu() + frontEnd2.Books() +
                FrontEnd2.menu() + FrontEnd2.invalid() +
                FrontEnd2.menu() + FrontEnd2.quit());
    }

    @Test
    public void should_check_out_a_book() throws Exception {
        consoleInput("1\n2\n1\n1\n0\n");

        FrontEnd2 frontEnd2 = initFront();

        String backupBooks = frontEnd2.Books();

        frontEnd2.displayMenu();
        assertEquals(outContent.toString(),
                FrontEnd2.menu() + backupBooks +
                FrontEnd2.menu() + FrontEnd2.checkoutContent(Item.TYPES.Book) + FrontEnd2.checkoutSuccessContent(Item.TYPES.Book) +
                FrontEnd2.menu() + frontEnd2.Books() +
                FrontEnd2.menu() + FrontEnd2.quit());
        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Book, 0).size(), is(3));
    }

    @Test
    public void should_check_out_a_movie() throws Exception {
        consoleInput("5\n6\n2\n3\n5\n0\n");

        FrontEnd2 front = initFront();

        String backupMovies = front.Movies();

        //checkout success one movie
        front.displayMenu();

        assertEquals(outContent.toString(),
                FrontEnd2.menu() + backupMovies +
                FrontEnd2.menu() + FrontEnd2.checkoutContent(Item.TYPES.Movie) + FrontEnd2.checkoutFailedContent(Item.TYPES.Movie) +
                FrontEnd2.checkoutContent(Item.TYPES.Movie) + FrontEnd2.checkoutSuccessContent(Item.TYPES.Movie) +
                FrontEnd2.menu() + front.Movies() +
                FrontEnd2.menu() + FrontEnd2.quit());
        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Movie, 0).size(), is(2));
    }



    @Test
    public void should_check_out_a_book_failed() throws Exception {

        consoleInput("1\n2\n3\n1\n1\n0\n");

        FrontEnd2 frontEnd2 = initFront();

        String backupBooks = frontEnd2.Books();

        frontEnd2.displayMenu();
        assertEquals(outContent.toString(),
                FrontEnd2.menu() + backupBooks +
                        FrontEnd2.menu() + FrontEnd2.checkoutContent(Item.TYPES.Book) + FrontEnd2.checkoutFailedContent(Item.TYPES.Book) +
                        FrontEnd2.checkoutContent(Item.TYPES.Book) + FrontEnd2.checkoutSuccessContent(Item.TYPES.Book) +
                        FrontEnd2.menu() + frontEnd2.Books() +
                        FrontEnd2.menu() + FrontEnd2.quit());
        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Book, 0).size(), is(3));
        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Movie, 0).size(), is(3));
    }

    @Test
    public void should_return_books() throws Exception {

        consoleInput("1\n2\n1\n2\n4\n3\n4\n1\n0\n");

        FrontEnd2 front = initFront();


        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Book, 0).size(), is(4));

        String backup = front.Books();
        front.displayMenu();

        assertEquals(outContent.toString(), FrontEnd2.menu() + backup +
                FrontEnd2.menu() + FrontEnd2.checkoutContent(Item.TYPES.Book) + FrontEnd2.checkoutSuccessContent(Item.TYPES.Book) +
                FrontEnd2.menu() + FrontEnd2.checkoutContent(Item.TYPES.Book) + FrontEnd2.checkoutSuccessContent(Item.TYPES.Book) +
                FrontEnd2.menu() + FrontEnd2.returnContent(Item.TYPES.Book) + FrontEnd2.returnSuccessContent(Item.TYPES.Book) +
                FrontEnd2.menu() + front.Books() +
                FrontEnd2.menu() + FrontEnd2.quit());
        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Book, 0).size(), is(3));
    }



    @Test
    public void should_return_movies() throws Exception {
        FrontEnd2 front = createFrontWithInput("5\n6\n5\n6\n6\n7\n6\n8\n0\n");

        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Movie, 0).size(), is(3));

        String backup = front.Movies();
        front.displayMenu();

        assertEquals(outContent.toString(), FrontEnd2.menu() + backup +
                FrontEnd2.menu() + FrontEnd2.checkoutContent(Item.TYPES.Movie) + FrontEnd2.checkoutSuccessContent(Item.TYPES.Movie) +
                FrontEnd2.menu() + FrontEnd2.checkoutContent(Item.TYPES.Movie) + FrontEnd2.checkoutSuccessContent(Item.TYPES.Movie) +
                FrontEnd2.menu() + FrontEnd2.returnContent(Item.TYPES.Movie) + FrontEnd2.returnSuccessContent(Item.TYPES.Movie) +
                FrontEnd2.menu() + front.unAvailable(Item.TYPES.Movie) +
                FrontEnd2.menu() + FrontEnd2.quit());
        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Movie, 0).size(), is(2));
    }

    private FrontEnd2 createFrontWithInput(String input) {
        consoleInput(input);
        return initFront();
    }


    @Test
    public void should_list_rented_books() throws Exception {
        FrontEnd2 front = createFrontWithInput("2\n2\n4\n0\n");

        front.displayMenu();

        assertEquals(outContent.toString(), FrontEnd2.menu() + FrontEnd2.checkoutContent(Item.TYPES.Book) + FrontEnd2.checkoutSuccessContent(Item.TYPES.Book) +
                FrontEnd2.menu() + front.unAvailable(Item.TYPES.Book) +
                FrontEnd2.menu() + FrontEnd2.quit());
        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Book, 1).size(), is (1));
        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Book, 0).size(), is (3));
    }


    @Test
    public void should_list_rented_movies() throws Exception {
        FrontEnd2 front = createFrontWithInput("6\n6\n8\n0\n");
        front.displayMenu();

        assertEquals(outContent.toString(), FrontEnd2.menu() + FrontEnd2.checkoutContent(Item.TYPES.Movie) + FrontEnd2.checkoutSuccessContent(Item.TYPES.Movie) +
                FrontEnd2.menu() + front.unAvailable(Item.TYPES.Movie) +
                FrontEnd2.menu() + FrontEnd2.quit());
        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Movie, 1).size(), is (1));
        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Movie, 0).size(), is (2));
    }
}
