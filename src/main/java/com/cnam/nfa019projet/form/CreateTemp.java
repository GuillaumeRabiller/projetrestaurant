package com.cnam.nfa019projet.form;

import com.cnam.nfa019projet.model.Frigo;

import java.util.List;

public class CreateTemp {

    //VARIABLES

    private List<Frigo> frigoList ;

    private long frigoId ;

    private float temperature ;

    //CONSTRUCTEUR VIDE

    public CreateTemp() {
    }

    //GETTERS ET SETTERS

    public List<Frigo> getFrigoList() {
        return frigoList;
    }

    public void setFrigoList(List<Frigo> frigoList) {
        this.frigoList = frigoList;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public long getFrigoId() {
        return frigoId;
    }

    public void setFrigoId(long frigoId) {
        this.frigoId = frigoId;
    }
}
