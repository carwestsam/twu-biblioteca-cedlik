package com.twu.biblioteca.service;

import com.twu.biblioteca.controller.ItemManager;
import com.twu.biblioteca.controller.ItemManagerTest;
import com.twu.biblioteca.controller.UserManager;
import com.twu.biblioteca.controller.UserManagerTest;
import com.twu.biblioteca.model.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LibraryTest {

    private Library library;
    private UserManager userManager;
    private ItemManager itemManager;

    @Before
    public void setup() throws Exception{
        Item.resetId();
        userManager = new UserManager();
        UserManagerTest.addUsers(userManager);

        itemManager = new ItemManager();
        ItemManagerTest.addItems(itemManager);

        library = new Library(userManager, itemManager);
    }

    @Test
    public void should_create_library() throws Exception {
        assertThat(library.getUserCount(), is(3));
        assertThat(library.getItemCount(), is(7));
    }

    @Test
    public void should_checkout_a_book_success() throws Exception {
        assertThat(library.getAvailableCountByType(Item.TYPES.Book), is(4));
        boolean result = library.checkout(userManager.getUserByName("user1"), itemManager.getItemByIdAndType(1, Item.TYPES.Book));
        assertThat(library.getAvailableCountByType(Item.TYPES.Book), is(3));
        assertThat(result, is(true));
        result = library.checkout(userManager.getUserByName("user1"), itemManager.getItemByIdAndType(1, Item.TYPES.Book));
        assertThat(library.getAvailableCountByType(Item.TYPES.Book), is(3));
        assertThat(result, is(false));
    }

    @Test
    public void should_get_checkout_book_list() throws Exception {
        assertThat(library.getAvailableCountByType(Item.TYPES.Book), is(4));

        library.checkout(userManager.getUserByName("user1"), itemManager.getItemByIdAndType(1, Item.TYPES.Book));
        library.checkout(userManager.getUserByName("user1"), itemManager.getItemByIdAndType(4, Item.TYPES.Book));
        library.checkout(userManager.getUserByName("user2"), itemManager.getItemByIdAndType(2, Item.TYPES.Book));

        assertThat(library.getRentListByUserNameAndItemType("user1", Item.TYPES.Book).size(), is(2));
        assertThat(library.getRentListByUserNameAndItemType("user2", Item.TYPES.Book).size(), is(1));
    }

    @Test
    public void should_return_checkout_book() throws Exception {
        library.checkout(userManager.getUserByName("user1"), itemManager.getItemByIdAndType(1, Item.TYPES.Book));
        library.checkout(userManager.getUserByName("user1"), itemManager.getItemByIdAndType(4, Item.TYPES.Book));
        library.checkout(userManager.getUserByName("user2"), itemManager.getItemByIdAndType(2, Item.TYPES.Book));

        library.unCheckout(userManager.getUserByName("user1"), itemManager.getItemByIdAndType(1, Item.TYPES.Book));

        ArrayList<Item> rentItems = library.getRentListByUserNameAndItemType("user1", Item.TYPES.Book);
        assertThat(rentItems.size(), is(1));
        assertThat(rentItems.get(0).getId() , is (4));
    }
}

