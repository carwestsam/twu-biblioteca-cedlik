package com.twu.biblioteca;

import com.twu.biblioteca.controller.ItemManager;
import com.twu.biblioteca.controller.UserManager;
import com.twu.biblioteca.model.Item;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.service.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by carwest on 15-8-2.
 */
public class Console {

    private final ItemManager itemManager;

    private Scanner scanner;
    private User root;
    private FrontEnd2 userFront;
    private UserManager userManager;
    public Console(User root, ItemManager itemManager, UserManager userManager) {
        this.root = root;
        this.itemManager = itemManager;
        this.userManager = userManager;
    }

    public static String welcome() {
        return "welcome\n";
    }

    public void displayWelcome() {
        display(welcome());
    }

    private void display(String str) {
        System.out.print(str);
    }

    public void displayMenu() {
        display(menu());
    }

    public static String menu() {
        return  "[1]\tLogin \n" +
                "[2]\tLogin as root\n" +
                "[0]\tQuit \n";
    }

    public void start() {
        scanner = new Scanner(System.in);
        while (true){
            displayMenu();

            int instr = scanner.nextInt();

            switch ( instr ){
                case 1:
                    userLogin();
                    break;
                case 2:
                    rootLogin();
                    break;
                case 0:
                    display(quit());
                    return;
                default:
                    return;
            }
        }
    }
    public void userLogin(){
        displayUserLoginContent();
        String userName = scanner.next();

        displayPasswordContent(1);
        String password = scanner.next();

        if ( userManager.checkLogin(userName, password) ){
            display(loginSuccessContent(1));
            userFront = new FrontEnd2(itemManager, userManager.getUserByName(userName), scanner);
            userFront.displayMenu();
        }else {
            display(loginFailedContent(1));
        }
    }

    private void displayUserLoginContent() {
        display(userLoginContent());
    }

    public static  String userLoginContent() {
        return "Please Input User Name:\n";

    }

    public void rootLogin() {

        displayPasswordContent(0);
        String pwd = scanner.next();

        if ( root.checkPassword(pwd) ){
            display(loginSuccessContent(0));
            userFront = new FrontEnd2(itemManager, root, new Scanner(System.in));
            display(rootInform());
        }else {
            display(loginFailedContent(0));
        }
    }

    public String rootInform() {
        ArrayList<Item> itemListByType = itemManager.getAvailableItemListByType(Item.TYPES.Book, 1);

        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        for ( Item item : itemListByType ){
            mapList.add(item.getHashMap());
        }

        return (new Table(itemManager.getHeaderListByType(Item.TYPES.Book), mapList)).Serial();

    }

    public static String loginFailedContent(int userType) {
        if ( 0 == userType){
            return "Login as root Failed\n";
        }else {
            return "Login as user Failed\n";
        }
    }

    private void displayPasswordContent(int type) {
        display(passwordContent(type));
    }

    public static String passwordContent(int type) {
        if ( 0 == type ){
            return "Please input root password:\n";
        }else {
            return "Please input user password:\n";
        }
    }

    public static String loginSuccessContent(int type) {
        if ( 0 == type ){
            return "Login as root success\n";
        }else {
            return "Login success\n";
        }
    }

    public static String quit() {
        return "Quit Biblioteca success\n";
    }
}

