package com.twu.biblioteca.service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TableTest {
    @Test
    public void should_print_a_table() throws Exception {

        ArrayList<HashMap<String, String>> array = init_tableArray();

        ArrayList<String> header = new ArrayList<>();
        header.add("a"); header.add("b");

        Table table = new Table(header, array, "title");

        assertEquals("title\na\tb\t\naa\tbb\t\nAA\tBB\t\n", table.Serial());
    }

    private ArrayList<HashMap<String, String>> init_tableArray() {
        HashMap<String, String> hashMap1 = new HashMap<>();
        HashMap<String, String> hashMap2 = new HashMap<>();

        hashMap1.put("a", "aa");
        hashMap1.put("b", "bb");
        hashMap2.put("a", "AA");
        hashMap2.put("b", "BB");

        ArrayList<HashMap<String, String>> array = new ArrayList<>();

        array.add(hashMap1);
        array.add(hashMap2);
        return array;
    }
}