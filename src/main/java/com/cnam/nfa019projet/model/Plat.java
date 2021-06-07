package com.cnam.nfa019projet.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Plat {

    //DECLARATION DES VARIABLES

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PLAT")
    private long id;

    @Column(nullable = false, name = "DECR_PLAT")
    @Size(min = 2, max = 64)
    private String description;

    @Column(nullable = false, name = "PRIX_PLAT")
    private float prix;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIE")
    private CategoriePlat categorie;

    @Transient
    private String categorieId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "PLAT_NOTE",
            joinColumns = {
                    @JoinColumn(name = "ID_PLAT", referencedColumnName = "ID_PLAT", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "ID_NOTE", referencedColumnName = "ID_NOTE", nullable = false, updatable = false)})
    private List<Note> notes;

    //CONSTRUCTEUR

    public Plat() {
    }

    //GETTERS ET SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public CategoriePlat getCategorie() {
        return categorie;
    }

    public void setCategorie(CategoriePlat categorie) {
        this.categorie = categorie;
    }

    public String getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(String categorieId) {
        this.categorieId = categorieId;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void addNotes(Note note) {
        note.addPlats(this);
        notes.add(note);
    }

    public void deleteNote(Note note) {
        note.deletePlats(this);
        notes.remove(note);
    }


    //Méthodes

    public void changeCategorie(CategoriePlat newCategorie) {
        this.getCategorie().deletePlat(this);
        newCategorie.addPlats(this);
    }

}
