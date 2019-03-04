package com.company;

public class Contact implements Comparable {
    String namef;
    String namel;
    String[] names;

    String num;

    String add;

    public Contact(String namef, String namel, String num, String add) {
        this.namef = namef;
        this.namel = namel;

        this.num = num;

        this.add = add;
    }

    public String getNamef() {
        return namef;
    }

    public void setNamef(String namef) {
        this.namef = namef;
    }

    public String getNamel() {
        return namel;
    }

    public void setNamel(String namel) {
        this.namel = namel;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    public String toString() {
        if(namef.equals("")) {
            return namel;
        }
        return namel + ", " + namef;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Contact) {
            char a = (this.toString().toLowerCase()).toCharArray()[0];
            char b = (((Contact) o).toString().toLowerCase()).toCharArray()[0];

            return(b - a);
        }
        return 0;
    }
}
