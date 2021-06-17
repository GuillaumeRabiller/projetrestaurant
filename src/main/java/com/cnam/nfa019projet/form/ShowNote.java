package com.cnam.nfa019projet.form;

import com.cnam.nfa019projet.model.Plat;
import java.util.List;

public class ShowNote {

    //VARIABLES

    private long idNote;
    private int noTable ;
    private String description ;
    private int couvert ;

    //CONSTRUCTEUR VIDE

    public ShowNote() {
    }

    //GETTERS ET SETTERS

    public long getIdNote() {
        return idNote;
    }

    public void setIdNote(long idNote) {
        this.idNote = idNote;
    }

    public int getNoTable() {
        return noTable;
    }

    public void setNoTable(int noTable) {
        this.noTable = noTable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCouvert() {
        return couvert;
    }

    public void setCouvert(int couvert) {
        this.couvert = couvert;
    }

}
