package com.cnam.nfa019projet.form;

public class ShowPlatNote {

    //VARIABLES

    private String nomCategorie ;
    private String description ;
    private float prix ;
    private int quantite;
    private long idPlat;

    //CONSTRUCTEUR VIDE

    public ShowPlatNote() {
    }

    //GETTERS ET SETTERS

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public long getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(long idPlat) {
        this.idPlat = idPlat;
    }
}
