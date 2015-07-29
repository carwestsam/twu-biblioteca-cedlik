package com.twu.biblioteca;

import com.twu.biblioteca.controller.BookManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

    public void displayWelcome() {
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

    public void listDetailedBooks() {
        final ArrayList<String> bookDetailsHeader = bookManager.getBookDetailsHeader();
        System.out.print("idx");

        ArrayList<HashMap<String, String>> mapped = bookManager.getMappedObjects();

        bookDetailsHeader.stream().forEach((header) -> {
            System.out.print("\t" + header);
        });
        System.out.println();

        for ( int i=0; i<bookManager.getCount(); i++){
            System.out.print(i+1);
            HashMap<String, String> dict = mapped.get(i);
            bookDetailsHeader.stream().forEach((head)->{
                System.out.print("\t" + dict.get(head));
            });
            System.out.println();
        }
    }

    public void displayMenu() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("[1]list all the books\n" +
                    "[0]quit\n" +
                    "Please input the instruction number:\n");
            int instr = scanner.nextInt();
            if (1 == instr) {
                listDetailedBooks();
            }else if ( 0 == instr ){
                System.out.print("Thanks for using\n");
                break;
            } else {
                System.out.println("Select a valid option!");
            }
        }
    }
}