package com.cnam.nfa019projet.form;

import org.springframework.format.annotation.DateTimeFormat;

public class DateStockForm {

    //VARIABLES

    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private String date ;

    private long id;

    //CONSTRUCTEUR VIDE

    public DateStockForm() {
    }

    //GETTERS ET SETTERS

    public String getDate() {
        return date;
    }

    public void setDate(String dateStock) {
        this.date = dateStock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
