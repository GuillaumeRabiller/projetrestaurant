package com.cnam.nfa019projet.form;

import com.cnam.nfa019projet.model.Table;

import java.util.List;

public class UpdateNoteForm {

    //Définition des variables
    private long id ;
    private int nbCouvert ;
    private List<Table> tables ;
    private long tableId;


    //Constructeur

    public UpdateNoteForm(long id, int nbCouvert, List<Table> tables) {
        this.id = id;
        this.nbCouvert = nbCouvert;
        this.tables = tables;
    }


    //Getters et Setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNbCouvert() {
        return nbCouvert;
    }

    public void setNbCouvert(int nbCouvert) {
        this.nbCouvert = nbCouvert;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }
}
