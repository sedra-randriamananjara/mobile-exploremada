package com.example.mobile_exploremada.models;

public class Lieu_imageModel {

    private int id;
    private int id_lieu;
    private String image;

    public Lieu_imageModel(int id, int id_lieu, String image) {
        this.id = id;
        this.id_lieu = id_lieu;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
