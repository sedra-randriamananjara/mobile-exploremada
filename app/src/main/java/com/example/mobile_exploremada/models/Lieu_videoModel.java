package com.example.mobile_exploremada.models;

public class Lieu_videoModel {

    private int id;
    private int id_lieu;
    private String video;

    public Lieu_videoModel(int id, int id_lieu, String video) {
        this.id = id;
        this.id_lieu = id_lieu;
        this.video = video;
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

    public String getvideo() {
        return video;
    }

    public void setvideo(String video) {
        this.video = video;
    }
}
