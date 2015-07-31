package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Item;
import com.twu.biblioteca.model.Movie;

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

    public ArrayList<Item> getItemListByType(Item.TYPES itemType) {
        ArrayList<Item> items = new ArrayList<>();
        for (Item anItemList : itemList) {
            if (itemType == anItemList.getType()) {
                items.add(anItemList);
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
}