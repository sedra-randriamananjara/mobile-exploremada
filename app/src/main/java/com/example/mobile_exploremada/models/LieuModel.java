package com.example.mobile_exploremada.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class LieuModel implements Parcelable {
    //Lieu Model
    private int id;
    private String nom;
    private String description_longue;
    private String description_courte;
    private String image_miniature;

    private String latitude;
    private String longitude;

    private String heure_ouverture;

    private String frais_entree;
    private String remarque_frais_entree;


    private String contact;
private String autres_informations;

    private int id_type_lieu;
    private int id_ville;

    private String heure_fermeture;

    private String remarque_horaire;
    private String nom_typelieu;
    private String nom_ville;
    private String image_detail;

    public LieuModel(int id, String nom, String description_longue, String description_courte, String image_miniature, String latitude, String longitude, String heure_ouverture, String frais_entree, String remarque_frais_entree, String contact, String autres_informations, int id_type_lieu, int id_ville, String heure_fermeture, String remarque_horaire, String nom_typelieu, String nom_ville, String image_detail) {
        this.id = id;
        this.nom = nom;
        this.description_longue = description_longue;
        this.description_courte = description_courte;
        this.image_miniature = image_miniature;
        this.latitude = latitude;
        this.longitude = longitude;
        this.heure_ouverture = heure_ouverture;
        this.frais_entree = frais_entree;
        this.remarque_frais_entree = remarque_frais_entree;
        this.contact = contact;
        this.autres_informations = autres_informations;
        this.id_type_lieu = id_type_lieu;
        this.id_ville = id_ville;
        this.heure_fermeture = heure_fermeture;
        this.remarque_horaire = remarque_horaire;
        this.nom_typelieu = nom_typelieu;
        this.nom_ville = nom_ville;
        this.image_detail = image_detail;
    }

    protected LieuModel(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        description_courte = in.readString();
        image_miniature = in.readString();
        id_type_lieu = in.readInt();
        nom_typelieu = in.readString();
        id_ville = in.readInt();
        nom_ville = in.readString();
    }

    public static final Creator<LieuModel> CREATOR = new Creator<LieuModel>() {
        @Override
        public LieuModel createFromParcel(Parcel in) {
            return new LieuModel(in);
        }

        @Override
        public LieuModel[] newArray(int size) {
            return new LieuModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription_courte() {
        return description_courte;
    }

    public String getImage_miniature() {
        return image_miniature;
    }

    public int getId_type_lieu() {
        return id_type_lieu;
    }

    public String getNom_typelieu() {
        return nom_typelieu;
    }

    public int getId_ville() {
        return id_ville;
    }

    public String getNom_ville() {
        return nom_ville;
    }

    public String getDescription_longue() {
        return description_longue;
    }

    public void setDescription_longue(String description_longue) {
        this.description_longue = description_longue;
    }

    public String getHeure_ouverture() {
        return heure_ouverture;
    }

    public void setHeure_ouverture(String heure_ouverture) {
        this.heure_ouverture = heure_ouverture;
    }

    public String getFrais_entree() {
        return frais_entree;
    }

    public void setFrais_entree(String frais_entree) {
        this.frais_entree = frais_entree;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage_detail() {
        return image_detail;
    }

    public void setImage_detail(String image_detail) {
        this.image_detail = image_detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nom);
        parcel.writeString(description_courte);
        parcel.writeString(image_miniature);
        parcel.writeInt(id_type_lieu);
        parcel.writeString(nom_typelieu);
        parcel.writeInt(id_ville);
        parcel.writeString(nom_ville);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription_courte(String description_courte) {
        this.description_courte = description_courte;
    }

    public void setImage_miniature(String image_miniature) {
        this.image_miniature = image_miniature;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRemarque_frais_entree() {
        return remarque_frais_entree;
    }

    public void setRemarque_frais_entree(String remarque_frais_entree) {
        this.remarque_frais_entree = remarque_frais_entree;
    }

    public String getAutres_informations() {
        return autres_informations;
    }

    public void setAutres_informations(String autres_informations) {
        this.autres_informations = autres_informations;
    }

    public void setId_type_lieu(int id_type_lieu) {
        this.id_type_lieu = id_type_lieu;
    }

    public void setId_ville(int id_ville) {
        this.id_ville = id_ville;
    }

    public String getHeure_fermeture() {
        return heure_fermeture;
    }

    public void setHeure_fermeture(String heure_fermeture) {
        this.heure_fermeture = heure_fermeture;
    }

    public String getRemarque_horaire() {
        return remarque_horaire;
    }

    public void setRemarque_horaire(String remarque_horaire) {
        this.remarque_horaire = remarque_horaire;
    }

    public void setNom_typelieu(String nom_typelieu) {
        this.nom_typelieu = nom_typelieu;
    }

    public void setNom_ville(String nom_ville) {
        this.nom_ville = nom_ville;
    }
}
