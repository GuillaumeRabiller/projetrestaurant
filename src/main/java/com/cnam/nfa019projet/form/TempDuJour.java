package com.cnam.nfa019projet.form;

public class TempDuJour {

    private String nomFrigo ;

    private float tempMatin = -100.0f ;

    private float tempAprem = -100.0f ;

    private boolean tempMatinDown = false ;

    private boolean tempMatinUp = false ;

    private boolean tempApremDown = false ;

    private boolean tempApremUp = false ;

    public TempDuJour() {
    }

    public String getNomFrigo() {
        return nomFrigo;
    }

    public void setNomFrigo(String nomFrigo) {
        this.nomFrigo = nomFrigo;
    }

    public float getTempMatin() {
        return tempMatin;
    }

    public void setTempMatin(float tempMatin) {
        this.tempMatin = tempMatin;
    }

    public float getTempAprem() {
        return tempAprem;
    }

    public void setTempAprem(float tempAprem) {
        this.tempAprem = tempAprem;
    }

    public boolean isTempMatinDown() {
        return tempMatinDown;
    }

    public void setTempMatinDown(boolean tempMatinDown) {
        this.tempMatinDown = tempMatinDown;
    }

    public boolean isTempMatinUp() {
        return tempMatinUp;
    }

    public void setTempMatinUp(boolean tempMatinUp) {
        this.tempMatinUp = tempMatinUp;
    }

    public boolean isTempApremDown() {
        return tempApremDown;
    }

    public void setTempApremDown(boolean tempApremDown) {
        this.tempApremDown = tempApremDown;
    }

    public boolean isTempApremUp() {
        return tempApremUp;
    }

    public void setTempApremUp(boolean tempApremUp) {
        this.tempApremUp = tempApremUp;
    }
}

