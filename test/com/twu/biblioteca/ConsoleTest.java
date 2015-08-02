package com.twu.biblioteca;

import com.twu.biblioteca.controller.ItemManager;
import com.twu.biblioteca.controller.UserManager;
import com.twu.biblioteca.controller.UserManagerTest;
import com.twu.biblioteca.model.Item;
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

public class ConsoleTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ItemManager itemManager;
    private Library library;
    private UserManager userManager;

    @Before
    public void setup() throws Exception{
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        Item.resetId();

        //itemManager = new ItemManager();
        //UserConsoleTest.addItems(itemManager);
    }

    @After
    public void cleanup() throws  Exception{
        System.setOut(null);
        System.setErr(null);
        outContent.reset();
    }

    private Console createConsole() {
        itemManager = new ItemManager();
        Item.resetId();
        UserConsoleTest.addItems(itemManager);

        User root = new User("root", "r654321", 0, "email", "phone");

        this.userManager = new UserManager();
        UserManagerTest.addUsers(userManager);

        library = new Library(userManager, itemManager);

        //userFront = new UserConsole(new Library(new UserManager(), new ItemManager()), new Scanner(System.in), new User("user1", "123456", 1, "123@g.com", "13912345678"));

        return new Console(root, library);
    }

    private Console createConsoleWithInput(String input) {
        Console console = createConsole();
        consoleInput(input);
        return console;
    }

    private void consoleInput(String instruction){
        ByteArrayInputStream input = new ByteArrayInputStream(instruction.getBytes());
        System.setIn(input);
    }

    @Test
    public void should_display_welcome() throws Exception {
        Console console = createConsole();

        console.displayWelcome();
        assertEquals(outContent.toString(), Console.welcome());
    }

    @Test
    public void should_display_menu() throws Exception {
        Console console = createConsole();

        console.displayMenu();
        assertEquals(outContent.toString(), Console.menu());
    }

    @Test
    public void should_login_as_librarian() throws Exception {
        Console console = createConsole();
        consoleInput("2\nr654321\n0\n");
        console.start();
        assertEquals(outContent.toString(),
                Console.menu() + Console.passwordContent(0) + Console.loginSuccessContent(0) +
                console.rootInform() +
                Console.menu() + Console.quit());
    }

    @Test
    public void should_login_as_librarian_failed() throws Exception {
        Console console = createConsole();
        consoleInput("2\nr123456\n0\n");
        console.start();
         assertEquals(outContent.toString(), Console.menu() + Console.passwordContent(0) + Console.loginFailedContent(0) + Console.menu() + Console.quit()) ;
    }

    @Test
    public void should_login_as_Common_User() throws Exception {
        Console console = createConsoleWithInput("1\nuser1\np123\n0\n0\n");
        console.start();
        assertEquals(outContent.toString(),
                Console.menu() + Console.userLoginContent() + Console.passwordContent(1) + Console.loginSuccessContent(1) +
                UserConsole.menu() + UserConsole.quit() +
                Console.menu() + Console.quit());
    }


    @Test
    public void should_login_as_Common_User_failed() throws Exception {
        Console console = createConsoleWithInput("1\nuser2\np123\n0\n");
        console.start();
        assertEquals(outContent.toString(),
                Console.menu() + Console.userLoginContent() + Console.passwordContent(1) + Console.loginFailedContent(1) +
                        Console.menu() + Console.quit());
    }

    @Test
    public void should_login_common_checkout_a_book_and_list_in_root() throws Exception {
        Console console = createConsoleWithInput("1\nuser1\np123\n2\n2\n0\n2\nr654321\n0\n");
        console.start();

        assertEquals(outContent.toString(), Console.menu() +
                Console.userLoginContent() + Console.passwordContent(1) + Console.loginSuccessContent(1) +
                UserConsole.menu() + UserConsole.checkoutContent(Item.TYPES.Book) + UserConsole.checkoutSuccessContent(Item.TYPES.Book) +
                UserConsole.menu() + UserConsole.quit() +
                Console.menu() + Console.passwordContent(0) + Console.loginSuccessContent(0) + console.rootInform() +
                Console.menu() + Console.quit());

        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Book, 1).size() , is (1));
    }

    @Test
    public void Two_user_checkout_Book_and_movie_and_librarian_list_it() throws Exception {
        Console console = createConsole();
        User user1 = library.getUserManager().getUserByName("user1");
        User user2 = library.getUserManager().getUserByName("user2");
        UserConsole userConsole1 = new UserConsole(library, new Scanner(System.in), user1);
        UserConsole userConsole2 = new UserConsole(library, new Scanner(System.in), user2);

        assertThat(userConsole1.getRentedList(Item.TYPES.Book).size(), is(0));
        assertThat(userConsole1.getRentedList(Item.TYPES.Movie).size(), is(0));
        assertThat(userConsole2.getRentedList(Item.TYPES.Book).size(), is(0));
        assertThat(userConsole2.getRentedList(Item.TYPES.Movie).size(), is(0));

        userConsole1.checkout(2, Item.TYPES.Book);
        userConsole1.checkout(5, Item.TYPES.Movie);
        userConsole2.checkout(1, Item.TYPES.Book);

        assertThat(userConsole1.getRentedList(Item.TYPES.Book).size(), is(1));
        assertThat(userConsole1.getRentedList(Item.TYPES.Movie).size(), is(1));
        assertThat(userConsole2.getRentedList(Item.TYPES.Book).size(), is(1));
        assertThat(userConsole2.getRentedList(Item.TYPES.Movie).size(), is(0));

        userConsole1.handback(2, Item.TYPES.Book);
        assertThat(userConsole1.getRentedList(Item.TYPES.Book).size(), is(0));
        userConsole1.handback(5, Item.TYPES.Movie);
        assertThat(userConsole1.getRentedList(Item.TYPES.Movie).size(), is(0));
        assertThat(userConsole2.getRentedList(Item.TYPES.Book).size(), is(1));
        assertThat(userConsole2.getRentedList(Item.TYPES.Movie).size(), is(0));

    }
}
