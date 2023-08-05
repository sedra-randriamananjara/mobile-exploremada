package com.example.mobile_exploremada.models;

public class RegisterModel {
    private String statue;
    private String message;
    private UserData data;
    public RegisterModel(String statue, String message, UserData data) {
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

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }
}
