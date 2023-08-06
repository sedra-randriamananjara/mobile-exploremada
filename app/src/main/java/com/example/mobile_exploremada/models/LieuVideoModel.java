package com.example.mobile_exploremada.models;

public class LieuVideoModel {
    private int id;
    private int id_lieu;
    private String video;

    public LieuVideoModel(int id, int id_lieu, String video) {
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
