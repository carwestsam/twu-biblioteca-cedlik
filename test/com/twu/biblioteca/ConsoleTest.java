package com.twu.biblioteca;

import com.twu.biblioteca.controller.ItemManager;
import com.twu.biblioteca.controller.UserManager;
import com.twu.biblioteca.controller.UserManagerTest;
import com.twu.biblioteca.model.Item;
import com.twu.biblioteca.model.User;
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
    private FrontEnd2 userFront;

    @Before
    public void setup() throws Exception{
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        Item.resetId();

        itemManager = new ItemManager();
        FrontEndTest.addItems(itemManager);
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
        FrontEndTest.addItems(itemManager);
        userFront = new FrontEnd2(itemManager, new User("user1", "123456", 1, "123@g.com", "13912345678"), new Scanner(System.in));
        User root = new User("root", "r654321", 0, "email", "phone");

        UserManager userManager = new UserManager();

        UserManagerTest.addUsers(userManager);

        return new Console(root, itemManager, userManager);
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
        assertEquals(outContent.toString(), Console.menu() + Console.passwordContent(0) + Console.loginSuccessContent(0) + userFront.available(Item.TYPES.Book, 1) + Console.menu() + Console.quit());
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
                FrontEnd2.menu() + FrontEnd2.quit() +
                Console.menu() + Console.quit());
    }

    private Console createConsoleWithInput(String input) {
        Console console = createConsole();
        consoleInput(input);
        return console;
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
                FrontEnd2.menu() + FrontEnd2.checkoutContent(Item.TYPES.Book) + FrontEnd2.checkoutSuccessContent(Item.TYPES.Book) +
                FrontEnd2.menu() + FrontEnd2.quit() +
                Console.menu() + Console.passwordContent(0) + Console.loginSuccessContent(0) + console.rootInform() +
                Console.menu() + Console.quit());

        assertThat(itemManager.getItemListByTypeAndCheckout(Item.TYPES.Book, 1).size() , is (1));
    }
}
