package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.User;

import java.util.Hashtable;

/**
 * Created by carwest on 15-8-2.
 */
public class UserManager {

    private Hashtable<String, User> userTable = new Hashtable<>();

    public boolean add(User user) {
        if (user.getUserName().length() != 0 && !userTable.containsKey(user.getUserName())){
            userTable.put(user.getUserName(), user);
            return true;
        }else {
            return false;
        }
    }

    public int getUserCount() {
        return userTable.size();
    }

    public boolean checkLogin(String userName, String password) {
        return ( userTable.containsKey(userName) && userTable.get(userName).checkPassword(password));
    }

    public User getUserByName(String userName) {
        if ( userTable.containsKey(userName) ){
            return userTable.get(userName);
        }else {
            return null;
        }
    }
}
