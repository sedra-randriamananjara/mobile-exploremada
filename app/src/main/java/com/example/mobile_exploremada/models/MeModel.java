package com.example.mobile_exploremada.models;

public class MeModel {
    private int id;
    private String nom;
    private String email;
    private String motdepasse;
    private int id_langue;
    private String contact;

    public MeModel(int id, String nom, String email, String motdepasse, int id_langue, String contact) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motdepasse = motdepasse;
        this.id_langue = id_langue;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public int getId_langue() {
        return id_langue;
    }

    public void setId_langue(int id_langue) {
        this.id_langue = id_langue;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
