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

    public FrontEnd2(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public String displayWelcome() {
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
        Scanner scanner = new Scanner(System.in);
        while ( true ){
            display(menu());
            int instr = scanner.nextInt();

            switch (instr){
                case 1:
                    displayBooks();
                    break;
                case 0:
                    display(quit());
                    return;
                default:
                    return;
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


}
