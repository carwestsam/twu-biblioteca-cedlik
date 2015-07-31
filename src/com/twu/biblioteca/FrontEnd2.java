package com.twu.biblioteca;

import com.twu.biblioteca.controller.ItemManager;
import com.twu.biblioteca.model.Item;
import com.twu.biblioteca.service.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by carwest on 15-7-31.
 */
public class FrontEnd2 {

    private final ItemManager itemManager;
    private Scanner scanner;

    public FrontEnd2(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public String welcome() {
        return "Welcome to Biblioteca\n";
    }

    public void display(String str) {
        System.out.print(str);
    }

    public void displayBooks() {
        display(Books());
    }

    public String Books() {
        ArrayList<Item> itemListByType = itemManager.getItemListByType(Item.TYPES.Book);
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        for ( Item item : itemListByType ){
            mapList.add(item.getHashMap());
        }
        return (new Table(itemManager.getHeaderListByType(Item.TYPES.Book), mapList)).Serial();
    }

    public void displayMenu() {
        scanner = new Scanner(System.in);
        while ( true ){
            display(menu());
            int instr = scanner.nextInt();

            switch (instr){
                case 1:
                    displayBooks();
                    break;
                case 2:
                    displayCheckout(Item.TYPES.Book);
                    break;
                case 0:
                    display(quit());
                    return;
                default:
                    display(invalid());
                    break;
            }
        }
    }

    private void displayCheckout(Item.TYPES itemType) {
        while (true){
            display(checkoutContent());
            int instr = scanner.nextInt();

            int statu = itemManager.checkoutById(instr, itemType);

            if ( 0 == statu ){
                display(checkoutSuccessContent());
                break;
            }else if ( 1 == statu ){
                display(checkoutFailedContent());
            }else {
                display(checkoutFailedContent());
            }
        }

    }

    public static String menu() {
        String menu = "\n---\n\n" +
                "[1]list all the books\n" +
                "[2]Checkout book\n" +
                "[3]Return book\n" +
                "[4]List all the borrowed books\n" +
                "[0]quit\n" +
                "Please input the instruction number:\n";
        return menu;
    }
    public static String quit(){
        return "Thanks for using\n";
    }


    public static String invalid() {
        return "Select a valid option!";
    }

    public static String checkoutContent() {
        return "Choose the book number to checkout(0 to quit):\n";
    }

    public static String checkoutSuccessContent() {
        return "Thank you! Enjoy the book\n";
    }

    public static String checkoutFailedContent() {
        return "That book is not available.\n";
    }
}
