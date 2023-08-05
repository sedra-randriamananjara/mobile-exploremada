package com.example.mobile_exploremada.models;

public class UserData {
    private String statue;
    private String message;
    private int data;

    public UserData(String statue, String message, int data) {
        this.statue = statue;
        this.message = message;
        this.data = data;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
