package com.twu.biblioteca.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by carwest on 15-7-31.
 */
public abstract class Item {
    int checkout = 0;
    private TYPES type;
    private static int ID_INC = 0;
    private int id = 0;

    public boolean checkout() {
        checkout = 1;
        return true;
    }
    public int getCheckout(){
        return checkout;
    }

    public enum TYPES {Book, Movie};

    public static void resetId(){
        ID_INC = 0;
    }
    public static int getNextId(){
        return ++ID_INC;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }


    public void setType(TYPES type) {
        this.type = type;
    }
    public TYPES getType() {
        return type;
    }

    public abstract ArrayList<String> getDictNames();
    public abstract HashMap<String,String> getHashMap();
}
