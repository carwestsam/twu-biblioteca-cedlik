package com.twu.biblioteca.model;

/**
 * Created by carwest on 15-8-1.
 */
public class User {

    private static int ID_INC = 0;
    private final String password;
    private final String email;
    private final int pri;
    private final String phone;
    private String userName;
    private int id;

    public User(String userName, String password, int pri, String email, String phone) {
        setId(nextID());
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.pri = pri;
        this.phone = phone;
    }

    private int nextID() {
        return ++ID_INC;
    }
    public static void resetID(){
        ID_INC = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isRoot() {
        if ( pri == 0){
            return true;
        }else {
            return false;
        }
    }

    public String getPhone() {
        return phone;
    }

    public boolean checkPassword(String str) {
        return password.equals(str);
    }
}
