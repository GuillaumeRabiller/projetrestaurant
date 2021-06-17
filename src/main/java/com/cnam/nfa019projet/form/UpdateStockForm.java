package com.cnam.nfa019projet.form;

public class UpdateStockForm {

    //VARIABLES

    private long id ;
    private String nomProduit ;
    private String statut ;

    //CONSTRUCTEUR

    public UpdateStockForm(long id, String nomProduit) {
        this.id = id;
        this.nomProduit = nomProduit;
    }

    //GETTERS ET SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
