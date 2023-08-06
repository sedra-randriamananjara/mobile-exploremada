package com.example.mobile_exploremada.models;

public class LieuVideoModel {
    private int id;
    private int id_lieu;
    private String video;

    private String nom;
    private String description_courte;
    private String image_miniature;
    private String nom_ville;

    public LieuVideoModel(int id, int id_lieu, String video, String nom, String description_courte, String image_miniature, String nom_ville) {
        this.id = id;
        this.id_lieu = id_lieu;
        this.video = video;
        this.nom = nom;
        this.description_courte = description_courte;
        this.image_miniature = image_miniature;
        this.nom_ville = nom_ville;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_lieu() {
        return id_lieu;
    }

    public void setId_lieu(int id_lieu) {
        this.id_lieu = id_lieu;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription_courte() {
        return description_courte;
    }

    public void setDescription_courte(String description_courte) {
        this.description_courte = description_courte;
    }

    public String getImage_miniature() {
        return image_miniature;
    }

    public void setImage_miniature(String image_miniature) {
        this.image_miniature = image_miniature;
    }

    public String getNom_ville() {
        return nom_ville;
    }

    public void setNom_ville(String nom_ville) {
        this.nom_ville = nom_ville;
    }
}
