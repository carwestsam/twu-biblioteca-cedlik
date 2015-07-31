package com.twu.biblioteca.service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by carwest on 15-7-31.
 */
public class Table {

    private final ArrayList<String> header;
    private final ArrayList<HashMap<String, String>> array;
    private final String title;

    public Table(ArrayList<String> header, ArrayList<HashMap<String, String>> array, String title) {
        this.header = header;
        this.array = array;
        this.title = title;
    }

    public Table(ArrayList<String> header, ArrayList<HashMap<String, String>> array) {
        this.header = header;
        this.array = array;
        this.title = "";
    }

    public String Serial() {
        String ans = "";
        if ( header.size() == 0 ){
            return ans;
        }
        if (title.length() > 1){
            ans = title + "\n";
        }
        for (String aHeader : header) {
            ans += aHeader + "\t";
        }
        ans += "\n";
        for (HashMap<String, String> hashMap : array) {
            for (String aHeader : header) {
                ans += hashMap.get(aHeader) + "\t";
            }
            ans += "\n";
        }
        return ans;
    }
}
