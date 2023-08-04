package com.example.mobile_exploremada.models;

public class LoginModel {
    private String statue;
    private String message;
    private String data;

    public LoginModel(String statue, String message, String data) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
