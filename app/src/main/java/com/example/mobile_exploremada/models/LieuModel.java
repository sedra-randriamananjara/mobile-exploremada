package com.example.mobile_exploremada.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class LieuModel implements Parcelable {
    //Lieu Model
    private int id;
    private String nom;
    private String description_courte;
    private String image_miniature;
    private int id_type_lieu;
    private String nom_typelieu;
    private int id_ville;
    private String nom_ville;

    public LieuModel(int id, String nom, String description_courte, String image_miniature, int id_type_lieu, String nom_typelieu, int id_ville, String nom_ville) {
        this.id = id;
        this.nom = nom;
        this.description_courte = description_courte;
        this.image_miniature = image_miniature;
        this.id_type_lieu = id_type_lieu;
        this.nom_typelieu = nom_typelieu;
        this.id_ville = id_ville;
        this.nom_ville = nom_ville;
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
}
