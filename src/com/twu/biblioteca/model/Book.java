package com.twu.biblioteca.model;

import java.util.HashMap;

/**
 * Created by carwest on 15-7-29.
 */
public class Book {

    private int year;
    private String author;
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, String author, int year) {
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

    public HashMap<String, String> getMapped() {
        HashMap<String, String> dict = new HashMap<>();
        dict.put("title", this.title);
        dict.put("author", this.author);
        dict.put("year", Integer.toString(this.year));
        return dict;
    }
}
