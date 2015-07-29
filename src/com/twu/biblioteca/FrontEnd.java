package com.twu.biblioteca;

import com.twu.biblioteca.controller.BookManager;

import java.util.ArrayList;

/**
 * Created by carwest on 15-7-29.
 */
public class FrontEnd {

    private final BookManager bookManager;

    public FrontEnd(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public FrontEnd() {
        this.bookManager = null;
    }

    public void start() {
        System.out.println("Welcome to Biblioteca");
    }

    public void listBooks() {
        ArrayList<ArrayList<String>> table = bookManager.getFlatArray();
        consolePrintTable(table);
    }

    private void consolePrintTable(ArrayList<ArrayList<String>> table) {
        for ( int i=0; i<table.size(); i++ ){
            System.out.print(i+1);
            System.out.print("\t");

            ArrayList<String> record = table.get(i);
            if ( record.size() > 0 ) System.out.print(record.get(0));
            for ( int j=1; j<record.size(); j++ ){
                System.out.print("\t"+record.get(j));
            }
            System.out.print("\n");
        }
    }
}
