package com.twu.biblioteca.service;

import com.twu.biblioteca.controller.ItemManager;
import com.twu.biblioteca.controller.UserManager;

/**
 * Created by carwest on 15-8-2.
 */
public class Library {

    private final UserManager userManager;
    private final ItemManager itemManager;

    public Library(UserManager userManager, ItemManager itemManager) {
        this.userManager = userManager;
        this.itemManager = itemManager;
    }

    public Integer getUserCount() {
        return userManager.getUserCount();
    }

    public Integer getItemCount() {
        return itemManager.getItemCount();
    }
}
