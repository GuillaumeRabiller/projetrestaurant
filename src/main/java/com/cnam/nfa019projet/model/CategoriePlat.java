package com.cnam.nfa019projet.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class CategoriePlat {

    //DECLARATION DES VARIABLES

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_CATEGORIE")
    private long id ;

    @Size(min=3, max=64)
    @Column(nullable = false, name="NOM_CATEGORIE")
    private String nomCategorie ;

    @Column(nullable = false, name="TVA_CATEGORIE")
    private float tva ;

    @OneToMany(mappedBy = "categorie")
    private List<Plat> plats ;

    //CONCTRUCTEUR

    public CategoriePlat() {
    }

    //GETTERS ET SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public float getTva() {
        return tva;
    }

    public void setTva(float tva) {
        this.tva = tva;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    public void addPlats(Plat plat) {
        plat.setCategorie(this);
        plats.add(plat);
    }


    //METHODES

    public void deletePlat(Plat plat) {
        plats.remove(plat);
    }

}
