package com.twu.biblioteca.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by carwest on 15-7-29.
 */
public class Book extends Item{

    private int year;
    private String author;
    private String title;

    public Book(String title) {
        setType(TYPES.Book);
        setId(getNextId());
        this.title = title;
    }

    public Book(String title, String author, int year) {
        setType(TYPES.Book);
        setId(getNextId());
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public HashMap<String, String> getHashMap() {
        HashMap<String, String> dict = new HashMap<>();
        dict.put("id", Integer.toString(getId()));
        dict.put("title", this.title);
        dict.put("author", this.author);
        dict.put("year", Integer.toString(this.year));

        return dict;
    }

    public static ArrayList<String> getDictNamesStatic(){
        ArrayList<String> dict = new ArrayList<>();
        dict.add("id");dict.add("title");dict.add("author");dict.add("year");
        return dict;
    }

    @Override
    public ArrayList<String> getDictNames() {
        return getDictNamesStatic();
    }
}
