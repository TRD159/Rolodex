package com.company;

import java.util.Comparator;

public class Sortr implements Comparator<Contact> {
    @Override
    public int compare(Contact o1, Contact o2) {
        char w = o1.toString().toLowerCase().toCharArray()[0];
        char t = o2.toString().toLowerCase().toCharArray()[0];

        return w - t;
    }
}
