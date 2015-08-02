package com.twu.biblioteca.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {

    @Before
    public void init_user() throws Exception{
        User.resetID();
    }

    @Test
    public void should_create_user() throws Exception {
        User user1 = new User("user1", "123456", 0, "123@g.com", "13912345678");
        assertThat(user1.getUserName(), is("user1"));
        assertThat(user1.getId(), is(1));
        User user2 = new User("user2", "123456", 0, "123@g.com", "13912345678");
        assertThat(user2.getId(), is(2));

        assertThat(user2.getUserName(), is("user2"));
        assertThat(user2.checkPassword("123456"), is(true));
        assertThat(user2.getEmail(), is("123@g.com"));
        assertThat(user2.isRoot(), is(true));
        assertThat(user2.getPhone(), is("13912345678"));
    }

}