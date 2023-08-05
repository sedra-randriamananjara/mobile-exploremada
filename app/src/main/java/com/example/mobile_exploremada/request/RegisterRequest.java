package com.example.mobile_exploremada.request;

public class RegisterRequest {
        private String nom;
        private String email;
        private String motdepasse;
        private String  confirmmdp;

        private int id_langue;
        private String contact;

        public RegisterRequest(String nom, String email, String motdepasse,String confirmmdp, String contact) {
            this.nom = nom;
            this.email = email;
            this.motdepasse = motdepasse;
            this.contact = contact;
            this.confirmmdp=confirmmdp;
            this.id_langue=1;
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

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

    public String getConfirmmdp() {
        return confirmmdp;
    }

    public void setConfirmmdp(String confirmmdp) {
        this.confirmmdp = confirmmdp;
    }

    public int getId_langue() {
        return id_langue;
    }

    public void setId_langue(int id_langue) {
        this.id_langue = id_langue;
    }
}

