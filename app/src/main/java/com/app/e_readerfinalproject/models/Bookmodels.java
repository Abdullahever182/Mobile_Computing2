package com.app.e_readerfinalproject.models;

public class Bookmodels {
    int id;
    String bookname;

    public Bookmodels(int id, String bookname) {
        this.id = id;
        this.bookname = bookname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
}
