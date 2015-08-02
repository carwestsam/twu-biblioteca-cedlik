package com.twu.biblioteca.service;

import com.twu.biblioteca.controller.ItemManager;
import com.twu.biblioteca.controller.ItemManagerTest;
import com.twu.biblioteca.controller.UserManager;
import com.twu.biblioteca.controller.UserManagerTest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LibraryTest {

    private Library library;
    private UserManager userManager;
    private ItemManager itemManager;

    @Before
    public void setup() throws Exception{
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
    
}