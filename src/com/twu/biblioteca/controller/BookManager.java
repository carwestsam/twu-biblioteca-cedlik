package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by carwest on 15-7-29.
 */
public class BookManager {



    private ArrayList<Book> bookArray = new ArrayList<Book>();
    private ArrayList<Book> checkArray = new ArrayList<Book>();

    public Integer getCount() {
        return bookArray.size();
    }

    public void add(Book book) {
        bookArray.add(book);
    }

    public Book get(int idx) {
        return bookArray.get(idx);
    }

    public ArrayList<ArrayList<String>> getFlatArray() {
        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
        for ( int i=0; i<bookArray.size(); i++ ){
            ArrayList<String> record = new ArrayList<String>();
            record .add( bookArray.get(i).getTitle() );
            table.add(record);
        }
        return table;
    }

    public ArrayList<String> getBookDetailsHeader() {
        final ArrayList<String> headers = new ArrayList<String>();
        headers.add("title");
        headers.add("author");
        headers.add("year");
        return headers;
    }

    public ArrayList<HashMap<String, String>> getMappedObjects(ArrayList<Book> table) {
        ArrayList<HashMap<String, String>> objList = new ArrayList<>();
        table.stream().forEach((obj)->{objList.add(obj.getMapped());});
        return objList;
    }

    public ArrayList<Book> getBookArray() {
        return bookArray;
    }

    public int checkoutById(int idx) {
        if ( 0 <= idx && idx <bookArray.size() ){
            checkArray.add(bookArray.get(idx));
            bookArray.remove(idx);
            return 1;
        }else {
            return 0;
        }
    }

    public Integer getBorrowedCount() {
        return checkArray.size();
    }

    public ArrayList<Book> getCheckArray() {
        return checkArray;
    }

    public int handBackById(int idx) {
        if ( 0 <= idx && idx < checkArray.size()){
            bookArray.add(checkArray.get(idx));
            checkArray.remove(idx);
            return 1;
        }else {
            return 0;
        }
    }


}
