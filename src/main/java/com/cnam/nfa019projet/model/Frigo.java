package com.cnam.nfa019projet.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Frigo {

    //Définition des variables

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_FRIGO")
    private long id ;

    @Column(nullable = false, name = "NOM_FRIGO")
    @Size(min=3, max=30, message="Taille minimum de {min} et {max} au maximum")
    private String nomFrigo ;

    @Column(name = "DESC_FRIGO")
    @Size(min=2, max=64, message="Taille minimum de {min} et {max} au maximum")
    private String descFrigo ;

    @Column(name = "TEMP_MINI")
    private float tempMini ;

    @Column(name = "TEMP_MAXI")
    private float tempMaxi ;

    @OneToMany(mappedBy = "frigo")
    private List<ReleveTemp> relevesTemp;

    //CONSTRUCTEUR VIDE

    public Frigo() {
    }

    //GETTERS ET SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomFrigo() {
        return nomFrigo;
    }

    public void setNomFrigo(String nomFrigo) {
        this.nomFrigo = nomFrigo;
    }

    public String getDescFrigo() {
        return descFrigo;
    }

    public void setDescFrigo(String descFrigo) {
        this.descFrigo = descFrigo;
    }

    public float getTempMini() {
        return tempMini;
    }

    public void setTempMini(float tempMini) {
        this.tempMini = tempMini;
    }

    public float getTempMaxi() {
        return tempMaxi;
    }

    public void setTempMaxi(float tempMaxi) {
        this.tempMaxi = tempMaxi;
    }

    public List<ReleveTemp> getRelevesTemp() {
        return relevesTemp;
    }

    public void addReleveTemp(ReleveTemp releveTemp) {
        releveTemp.setFrigo(this);
        relevesTemp.add(releveTemp);
    }
}
