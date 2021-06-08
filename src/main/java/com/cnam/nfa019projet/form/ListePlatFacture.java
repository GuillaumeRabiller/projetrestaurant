package com.cnam.nfa019projet.form;

public class ListePlatFacture {

    //LISTE DES VARIABLES

    private String description ;

    private int quantite ;

    private float prix ;

    private float tva ;

    //CONSTRUCTEUR VIDE

    public ListePlatFacture() {
    }

    //GETTERS ET SETTERS

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getTva() {
        return tva;
    }

    public void setTva(float tva) {
        this.tva = tva;
    }
}
