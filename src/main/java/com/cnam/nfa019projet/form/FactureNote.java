package com.cnam.nfa019projet.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class FactureNote {

    //LISTE DES VARIABLES

    private int noTable ;

    private int nbCouvert ;

    private String serveur ;

    private List<ListePlatFacture> plats ;

    private BigDecimal sommeTTC ;

    private BigDecimal sommeHT ;

    private BigDecimal TVA10 ;

    private BigDecimal TVA20 ;

    private LocalDate dateFacture = LocalDate.now() ;

    private LocalTime heureFacture = LocalTime.now().truncatedTo(ChronoUnit.SECONDS) ;

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

    public BigDecimal getSommeTTC() {
        return sommeTTC;
    }

    public void setSommeTTC(BigDecimal sommeTTC) {
        this.sommeTTC = sommeTTC;
    }

    public BigDecimal getSommeHT() {
        return sommeHT;
    }

    public void setSommeHT(BigDecimal sommeHT) {
        this.sommeHT = sommeHT;
    }

    public BigDecimal getTVA10() {
        return TVA10;
    }

    public void setTVA10(BigDecimal TVA10) {
        this.TVA10 = TVA10;
    }

    public BigDecimal getTVA20() {
        return TVA20;
    }

    public void setTVA20(BigDecimal TVA20) {
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
