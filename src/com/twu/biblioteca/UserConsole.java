package com.twu.biblioteca;

import com.twu.biblioteca.controller.ItemManager;
import com.twu.biblioteca.model.Item;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.service.Library;
import com.twu.biblioteca.service.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Created by carwest on 15-7-31.
 */
public class UserConsole {

    //private final ItemManager itemManager;

    private final User user;
    private final Library library;
    private Scanner scanner;

    public UserConsole(Library library, Scanner scanner, User user) {
        //this.itemManager = libarayitemManager;
        this.library = library;
        this.user = user;
        this.scanner = scanner;
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
        return available(Item.TYPES.Book, 0);
    }

    public String Movies() {
        return available(Item.TYPES.Movie, 0);
    }

    public String unAvailable(Item.TYPES itemType) {
        return available(itemType, 1);
    }

    public String available(Item.TYPES itemType, int available) {
        ArrayList<Item> itemListByType = library.getItemManager().getItemListByTypeAndCheckout(itemType, available);
        return toTableString(itemType, itemListByType);
//        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
//        for ( Item item : itemListByType ){
//            mapList.add(item.getHashMap());
//        }
//        return (new Table(library.getItemManager().getHeaderListByType(itemType), mapList)).Serial();
    }

    public ArrayList<Item> getRentedList(Item.TYPES itemType){
        return library.getRentListByUserNameAndItemType(user.getUserName(), itemType);
    }

    public String toTableString(Item.TYPES itemType,  ArrayList<Item> itemList){
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        for ( Item item : itemList){
            mapList.add(item.getHashMap());
        }
        return (new Table(library.getItemManager().getHeaderListByType(itemType), mapList)).Serial();
    }

    public void displayMenu() {
        //scanner = new Scanner(System.in);
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
                    display(toTableString(Item.TYPES.Book, getRentedList(Item.TYPES.Book)));
                    //display(available(Item.TYPES.Book, 1));
                    break;
                case 5:
                    display(Movies());
                    break;
                case 6:
                    displayCheckout(Item.TYPES.Movie);
                    break;
                case 7:
                    displayReturn(Item.TYPES.Movie);
                    break;
                case 8:
                    display(toTableString(Item.TYPES.Movie, getRentedList(Item.TYPES.Movie)));
                    //display(available(Item.TYPES.Movie, 1));
                    break;
                case 9:
                    display(userInfo());
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
            if ( instr == 0 ){
                return ;
            }

            int statu = handback(instr, itemType);

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


    public int handback(int id, Item.TYPES itemType) {
        Item item = library.getItemManager().getItemByIdAndType(id, itemType);
        if ( item != null && library.unCheckout(user, item) ){
            return 0;
        }else {
            return 1;
        }
    }


    public String returnFailedContent(Item.TYPES itemType) {
        return getContent("returnFailedContent", itemType);
    }

    private void displayCheckout(Item.TYPES itemType) {
        while (true){
            display(checkoutContent(itemType));
            int instr = scanner.nextInt();

            if (instr == 0){
                break;
            }

            int statu = checkout( instr, itemType);

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

    public int checkout( int itemId, Item.TYPES itemType ){
        Item item = library.getItemManager().getItemByIdAndType(itemId, itemType);
        if ( item != null && library.checkout(user, item) ){
            return 0;
        }else {
            return 1;
        }
    }


    public static String menu() {
        String menu = "\n---\n\n" +
                "[1]List all the books\n" +
                "[2]Checkout book\n" +
                "[3]Return book\n" +
                "[4]List all borrowed Books\n" +
                "[5]List Movies\n" +
                "[6]Checkout Movie\n" +
                "[7]Return Movie\n" +
                "[8]List all borrowed Movies\n" +
                "[9]Display User Info\n" +
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


    public String userInfo() {
        return "---\nUser:\t" + user.getUserName() +"\nEmail:\t" + user.getEmail() + "\nPhone:\t" + user.getPhone() + "\n\n";
    }
}
