package com.cnam.nfa019projet.form;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowFacture {

    //VARIABLES

    public long idNote ;
    public LocalDate date ;
    public LocalTime heure ;
    public int noTable ;
    public String description ;
    public int couvert ;

    //CONSTRUCTEUR VIDE

    public ShowFacture() {
    }

    //GETTERS ET SETTERS

    public long getIdNote() {
        return idNote;
    }

    public void setIdNote(long idNote) {
        this.idNote = idNote;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
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
