package com.cnam.nfa019projet.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Note {

    //DECLARATION DES VARIABLES

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_NOTE")
    private long id ;

    @Column(name="DATE_NOTE", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime date ;

    @Column(name="NB_COUVERT_NOTE", nullable = false)
    private int couvert ;

    @ManyToOne
    @JoinColumn(name="ID_TABLE")
    private Table table;

    @Column(name="SERVEUR")
    private String serveur ;

    @ManyToMany(mappedBy = "notes", fetch = FetchType.LAZY)
    private List<Plat> plats;

    @Column(name="NOTE_REGLEE")
    private boolean reglement ;


    //CONSTRUCTEUR

    public Note() {
    }

    //GETTERS ET SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getCouvert() {
        return couvert;
    }

    public void setCouvert(int couvert) {
        this.couvert = couvert;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getServeur() { return serveur; }

    public void setServeur(String serveur) { this.serveur = serveur; }

    public List<Plat> getPlats() {
        return plats;
    }

    public void addPlats(Plat plat) {
        plats.add(plat);
    }

    public void deletePlats(Plat plat) { plats.remove(plat); }

    public boolean isReglement() { return reglement; }

    public void setReglement(boolean reglement) { this.reglement = reglement; }


    //METHODES

    public void changeTable(Table newTable){
        this.getTable().deleteNote(this);
        newTable.addNotes(this);
    }
}
