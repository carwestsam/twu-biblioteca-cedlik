package com.twu.biblioteca;

import com.twu.biblioteca.controller.ItemManager;
import com.twu.biblioteca.model.Item;
import com.twu.biblioteca.service.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
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
        ArrayList<Item> itemListByType = itemManager.getAvailableItemListByType(Item.TYPES.Book);
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        for ( Item item : itemListByType ){
            mapList.add(item.getHashMap());
        }
        return (new Table(itemManager.getHeaderListByType(Item.TYPES.Book), mapList)).Serial();
    }

    public String Movies() {
        ArrayList<Item> itemListByType = itemManager.getAvailableItemListByType(Item.TYPES.Movie);
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        for ( Item item : itemListByType ){
            mapList.add(item.getHashMap());
        }
        return (new Table(itemManager.getHeaderListByType(Item.TYPES.Movie), mapList)).Serial();
    }

    public String available(Item.TYPES itemType) {
        ArrayList<Item> itemListByType = itemManager.getAvailableItemListByType(itemType);
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        for ( Item item : itemListByType ){
            mapList.add(item.getHashMap());
        }
        return (new Table(itemManager.getHeaderListByType(itemType), mapList)).Serial();
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
                case 3:
                    displayReturn(Item.TYPES.Book);
                    break;
                case 4:
                    display(available(Item.TYPES.Book));
                    break;
                case 5:
                    display(Movies());
                    break;
                case 6:
                    displayCheckout(Item.TYPES.Movie);
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

    private void displayReturn(Item.TYPES itemType) {
        while ( true ){
            display(returnContent(itemType));

            int instr = scanner.nextInt();

            int statu = itemManager.returnItemById(instr, itemType);

            if ( 0 == statu ){
                display(returnSuccessContent(itemType));
                break;
            }else if ( 1 == statu ){
                display(returnFailedContent(itemType));
            }else {
                display(returnFailedContent(itemType));
            }
        }
    }

    private String returnFailedContent(Item.TYPES itemType) {
        return getContent("returnFailedContent", itemType);
    }

    private void displayCheckout(Item.TYPES itemType) {
        while (true){
            display(checkoutContent(itemType));
            int instr = scanner.nextInt();

            int statu = itemManager.checkoutById(instr, itemType);

            if ( 0 == statu ){
                display(checkoutSuccessContent(itemType));
                break;
            }else if ( 1 == statu ){
                display(checkoutFailedContent(itemType));
            }else {
                display(checkoutFailedContent(itemType));
            }
        }

    }
    public static String menu() {
        String menu = "\n---\n\n" +
                "[1]list all the books\n" +
                "[2]Checkout book\n" +
                "[3]Return book\n" +
                "[4]List all the borrowed books\n" +
                "[5]List Movies\n" +
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

    public static String checkoutContent(Item.TYPES itemType) {
        switch (itemType){
            case Book:
                return "Choose the book number to checkout(0 to quit):\n";
            case Movie:
                return "Choose the movie number to checkout(0 to quit):\n";
            default:
                return "Choose the number to checkout(0 to quit):\n";
        }
    }

    public static String checkoutSuccessContent(Item.TYPES itemType) {
        switch (itemType){
            case Book:
                return "Thank you! Enjoy the book\n";
            case Movie:
                return "Thank you! Enjoy the movie\n";
            default:
                return "Thank you!\n";
        }
    }

    public static String checkoutFailedContent(Item.TYPES itemType) {
        switch (itemType){
            case Book:
                return "That book is not available.\n";
            case Movie:
                return "That movie is not available.\n";
            default:
                return "not available.\n";
        }
    }


    public static String returnContent(Item.TYPES itemType) {
        switch (itemType){
            case Book:
                return "Input the book id to return(0 to quit):\n";
            case Movie:
                return "Input the movie id to return(0 to quit):\n";
            default:
                return "Input the item id to return(0 to quit):\n";
        }
    }



    public static String returnSuccessContent(Item.TYPES itemType) {
        return getContent("returnSuccessContent", itemType);
    }

    private static String getContent(String name, Item.TYPES itemType){
        Hashtable<String, HashMap<Item.TYPES, String>> table = initContent();
        return table.get(name).get(itemType);
    }

    private static Hashtable<String, HashMap<Item.TYPES, String>> initContent(){

        Hashtable<String, HashMap<Item.TYPES, String>> table = new Hashtable<>();
        table.put("returnSuccessContent", initReturnSuccessContent());
        table.put("returnFailedContent", initReturnFailedContent());
        return table;
    }

    private static HashMap<Item.TYPES, String> initReturnSuccessContent() {
        HashMap<Item.TYPES, String> contents = new HashMap<>();
        contents.put(Item.TYPES.Book, "Return book successful\n");
        contents.put(Item.TYPES.Movie, "Return movie successful\n");
        contents.put(Item.TYPES.DEF, "return item successful\n");
        return contents;
    }
    public static HashMap<Item.TYPES, String> initReturnFailedContent(){
        HashMap<Item.TYPES, String> contents = new HashMap<>();
        contents.put(Item.TYPES.Book, "Return book failed\n");
        contents.put(Item.TYPES.Movie, "Return movie failed\n");
        contents.put(Item.TYPES.DEF, "return item failed\n");
        return contents;
    }


}
