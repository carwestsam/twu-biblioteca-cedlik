package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Item;
import com.twu.biblioteca.model.Movie;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by carwest on 15-7-31.
 */
public class ItemManager {

    private ArrayList<Item> itemList = new ArrayList<Item>();

    public void add(Item item) {
        itemList.add(item);
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public ArrayList<Item> getItemListByTypeAndCheckout(Item.TYPES itemType, int available) {
        ArrayList<Item> items = new ArrayList<>();
        for (Item item : itemList) {
            if (itemType == item.getType() && item.getCheckout() == available) {
                items.add(item);
            }
        }
        return items;
    }


    public ArrayList<String> getHeaderListByType(Item.TYPES itemType) {
        switch (itemType){
            case Book:
                return Book.getDictNamesStatic();
            case Movie:
                return Movie.getDictNamesStatic();
            default:
                return new ArrayList<String>();
        }
    }

    public int checkoutById(int id, Item.TYPES itemType) {
        for ( Item item : itemList){
            if ( item.getId() == id && item.getType() == itemType ){
                item.checkout();
                return 0;
            }
        }
        return 1;
    }

    public int returnItemById(int id, Item.TYPES itemType) {
        for ( Item item : itemList){
            if ( item.getId() == id ){
                if ( item.getCheckout() == 1 && item.getType() == itemType ){
                    item.unCheckout();
                    return 0;
                }else {
                    return 1;
                }
            }
        }
        return 1;
    }

    public int getItemCount() {
        return itemList.size();
    }

    public Item getItemByIdAndType(int id, Item.TYPES itemType) {
        for ( Item item : itemList ){
            if ( item.getId() == id ){
                if ( item.getType() == itemType){
                    return item;
                }else {
                    return null;
                }
            }
        }
        return null;
    }


    public ArrayList<Item> getAvailableItemListByType(Item.TYPES itemType) {
        ArrayList<Item> items = new ArrayList<>();
        for ( Item item : itemList ) {
            if ((item.getType() == itemType) && (item.getCheckout()==0) ){
                items.add(item);
            }
        }
        return items;
    }
}
