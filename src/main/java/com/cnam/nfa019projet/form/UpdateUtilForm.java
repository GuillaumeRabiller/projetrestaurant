package com.cnam.nfa019projet.form;

import com.cnam.nfa019projet.model.RoleUtilisateur;

public class UpdateUtilForm {

    //VARIABLES

    private long id ;
    private String nom ;
    private String prenom ;
    private String email ;
    private String login ;
    private String password ;
    private RoleUtilisateur role ;

    //CONSTRUCTEUR

    public UpdateUtilForm(long id, String nom, String prenom, String email, String login, String password, RoleUtilisateur role) {
        this.id = id ;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    //GETTERS ET SETTERS

    public long getid() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public RoleUtilisateur getRole() {
        return role;
    }
}
