package com.cnam.nfa019projet.form;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class FactureNote {

    //LISTE DES VARIABLES

    private int noTable ;

    private int nbCouvert ;

    private String serveur ;

    private List<ListePlatFacture> plats ;

    private float sommeTTC ;

    private float sommeHT ;

    private float TVA10 ;

    private float TVA20 ;

    private LocalDate dateFacture = LocalDate.now() ;

    private LocalTime heureFacture = LocalTime.now() ;

    private long idNote ;

    //CONSTRUCTEUR VIDE

    public FactureNote() {
    }

    //GETTERS ET SETTERS

    public int getNoTable() {
        return noTable;
    }

    public void setNoTable(int noTable) {
        this.noTable = noTable;
    }

    public int getNbCouvert() {
        return nbCouvert;
    }

    public void setNbCouvert(int nbCouvert) {
        this.nbCouvert = nbCouvert;
    }

    public String getServeur() {
        return serveur;
    }

    public void setServeur(String serveur) {
        this.serveur = serveur;
    }

    public List<ListePlatFacture> getPlats() {
        return plats;
    }

    public void setPlats(List<ListePlatFacture> plats) {
        this.plats = plats;
    }

    public float getSommeTTC() {
        return sommeTTC;
    }

    public void setSommeTTC(float sommeTTC) {
        this.sommeTTC = sommeTTC;
    }

    public float getSommeHT() {
        return sommeHT;
    }

    public void setSommeHT(float sommeHT) {
        this.sommeHT = sommeHT;
    }

    public float getTVA10() {
        return TVA10;
    }

    public void setTVA10(float TVA10) {
        this.TVA10 = TVA10;
    }

    public float getTVA20() {
        return TVA20;
    }

    public void setTVA20(float TVA20) {
        this.TVA20 = TVA20;
    }

    public LocalDate getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
    }

    public LocalTime getHeureFacture() {
        return heureFacture;
    }

    public void setHeureFacture(LocalTime heureFacture) {
        this.heureFacture = heureFacture;
    }

    public long getIdNote() {
        return idNote;
    }

    public void setIdNote(long idNote) {
        this.idNote = idNote;
    }
}
