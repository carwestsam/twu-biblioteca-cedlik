package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserManagerTest {

    private UserManager userManager;

    @Before
    public void init()throws Exception{
        userManager = new UserManager();
    }

    public static void addUsers(UserManager userManager) {
        userManager.add(new User("user1", "p123", 1, "a@a.com", "139"));
        userManager.add(new User("user2", "r321", 1, "b@b.com", "155"));
        userManager.add(new User("user3", "b312", 1, "c@c.com", "138"));
    }

    @Test
    public void should_create_User_List() throws Exception {
        addUsers(userManager);
        assertThat(userManager.getUserCount(), is(3));
    }

    @Test
    public void should_check_login_with_userName_and_password() throws Exception {
        addUsers(userManager);
        assertThat(userManager.checkLogin("user1", "p123"), is(true));
        assertThat(userManager.checkLogin("user2", "p321"), is(false));
        assertThat(userManager.checkLogin("user3", "b312"), is(true));
    }
}
