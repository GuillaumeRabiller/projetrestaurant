package com.cnam.nfa019projet.form;

import java.time.LocalDate;
import java.time.LocalTime;

public class HistoriqueTemp {

    //VARIABLES

    private LocalDate date ;
    private LocalTime heure ;
    private float temp ;
    private String utilisateur ;
    private boolean tempDown = false ;
    private boolean tempUp = false ;

    //CONSTRUCTEUR VIDE

    public HistoriqueTemp() {
    }

    //GETTERS ET SETTERS

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public boolean isTempDown() {
        return tempDown;
    }

    public void setTempDown(boolean tempDown) {
        this.tempDown = tempDown;
    }

    public boolean isTempUp() {
        return tempUp;
    }

    public void setTempUp(boolean tempUp) {
        this.tempUp = tempUp;
    }
}
