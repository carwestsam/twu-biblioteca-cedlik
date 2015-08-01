package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Item;
import com.twu.biblioteca.model.Movie;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemManagerTest {

    @Test
    public void should_create_item_list() throws Exception {
        ItemManager itemManager = createList();

        ArrayList<Item> itemList = itemManager.getItemList();

        assertThat(itemList.size(), is(4));
    }

    @Test
    public void should_return_Typed_List() throws Exception {
        ItemManager itemManager = createList();

        ArrayList<Item> books = itemManager.getAvailableItemListByType(Item.TYPES.Book);

        assertThat(books.size(), is(2));

        itemManager.add(new Book("book3", "ba3", 1999));

        ArrayList<Item> books2 = itemManager.getAvailableItemListByType(Item.TYPES.Book);

        assertThat(books2.size(), is(3));

        assertThat(((Book)books2.get(1)).getTitle(), is("book2"));
    }

    @Test
    public void should_return_Typed_header() throws Exception {
        ItemManager itemManager = createList();
        ArrayList<String> headers = itemManager.getHeaderListByType(Item.TYPES.Book);

        assertThat(headers.get(1), is((new Book("","",1)).getDictNames().get(1)));
        assertThat(headers.get(2), is((new Book("","",1)).getDictNames().get(2)));
    }

    private ItemManager createList() {
        ItemManager itemManager = new ItemManager();
        itemManager.add(new Book("book1", "ba1" ,1997));
        itemManager.add(new Book("book2", "ba2", 1998));
        itemManager.add(new Movie("movie1", 1998, "name1", 5));
        itemManager.add(new Movie("movie2", 1999, "name2", -1));
        return itemManager;
    }

}