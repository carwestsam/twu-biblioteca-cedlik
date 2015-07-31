package com.twu.biblioteca.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by carwest on 15-7-30.
 */
public class Movie extends Item{

    private String name;
    private int year;
    private String director;
    private int rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRating() {
        if ( rating  > 0 && rating < 11 ) {
            return Integer.toString(rating);
        }else {
            return "unrated";
        }
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Movie(String name, int year, String director, int rating) {
        setId(getNextId());
        setType(TYPES.Movie);
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public HashMap<String, String> getHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", Integer.toString(getId()));
        hashMap.put("name", getName());
        hashMap.put("year", Integer.toString(getYear()));
        hashMap.put("director", getDirector());
        hashMap.put("rating", getRating());
        return hashMap;
    }

    public ArrayList<String> getDictNames() {
        return getDictNamesStatic();
    }

    public static ArrayList<String> getDictNamesStatic() {
        ArrayList<String> dict = new ArrayList<>();
        dict.add("id");
        dict.add("name");
        dict.add("year");
        dict.add("director");
        dict.add("rating");
        return dict;
    }
}
