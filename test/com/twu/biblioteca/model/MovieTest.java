package com.twu.biblioteca.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MovieTest {
    @Before
    public void setUp() throws Exception {
        Item.resetId();
    }

    @Test
    public void create_Movie() throws Exception {
        Movie movie = new Movie("peom", 1969, "Jeffy", -1);
        assertEquals("peom", movie.getName());
        assertEquals(1969, movie.getYear());
        assertEquals("Jeffy", movie.getDirector());
        assertEquals("unrated", movie.getRating());
        assertEquals(Movie.TYPES.Movie, movie.getType());
        assertThat(movie.getId(), is(1));
    }

    @Test
    public void get_Object_Mapper() throws Exception {
        Movie movie = new Movie("name", 1, "J", 1);
        assertThat(movie.getId(), is(1));
        HashMap<String, String> hashMap = movie.getHashMap();
        assertEquals("name", hashMap.get("name"));
        assertEquals("1", hashMap.get("year"));
        assertEquals("J", hashMap.get("director"));
        assertEquals("1", hashMap.get("rating"));
    }

    @Test
    public void get_Serial_Movie() throws Exception {
        Movie movie = new Movie("name", 1, "J", 3);
        ArrayList<String> dictArray = movie.getDictNames();
        HashMap<String, String> hashMap = movie.getHashMap();
        assertEquals("name", hashMap.get(dictArray.get(0)));
        assertEquals("1", hashMap.get(dictArray.get(1)));
        assertEquals("J", hashMap.get(dictArray.get(2)));
        assertEquals("3", hashMap.get(dictArray.get(3)));
    }
}
