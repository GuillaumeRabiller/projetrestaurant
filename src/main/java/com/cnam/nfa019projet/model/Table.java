package com.cnam.nfa019projet.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@javax.persistence.Table(name="TABLE_RESTAU")
public class Table {

    //DECLARATION DES VARIABLES

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_TABLE")
    private long id ;

    @Column(nullable = false, name="NO_TABLE")
    private int noTable ;

    @Column(name="DECR_TABLE")
    @Size(min=2, max=64)
    private String description ;

    @OneToMany(mappedBy = "table")
    private List<Note> notes ;

    //CONSTRUCTEUR

    public Table() {
    }


    //GETTERS ET SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Note> getNotes() {
        return notes;
    }

    public void addNotes(Note note) {
        note.setTable(this);
        notes.add(note) ;
    }

    //Méthodes

    public void deleteNote(Note note) {
        notes.remove(note);
    }
}
