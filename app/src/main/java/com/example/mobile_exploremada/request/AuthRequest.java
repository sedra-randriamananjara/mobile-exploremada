package com.example.mobile_exploremada.request;

// AuthRequest.java
public class AuthRequest {
    private String email;
    private String motdepasse;

    public AuthRequest(String email, String motdepasse) {
        this.email = email;
        this.motdepasse = motdepasse;
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
}

