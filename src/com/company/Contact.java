package com.company;

import java.util.ArrayList;

public class Contact {
    String name;
    String[] names;

    public Contact(String name) {
        this.name = name;
        names = name.split(" ");
    }
}
