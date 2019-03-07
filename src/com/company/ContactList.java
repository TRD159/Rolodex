package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class ContactList<E> implements Serializable {
    ArrayList<E> data;

    ContactList() {
        data = new ArrayList<>();
    }

    public ArrayList<E> getData() {
        return data;
    }

    public void setData(ArrayList<E> data) {
        this.data = data;
    }


}
