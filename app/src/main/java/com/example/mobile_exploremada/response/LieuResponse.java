package com.example.mobile_exploremada.response;

import com.example.mobile_exploremada.models.LieuModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//This class is for single lieu request pour le liste seulement
public class LieuResponse {
    // 1- Find the Lieu
    @SerializedName("statue")
    @Expose()
    private String statue;

    @SerializedName("message")
    @Expose()
    private String message;

    @SerializedName("data")
    @Expose()
    private List<LieuModel> lieu;

    public List<LieuModel> getLieu(){
        return lieu;
    }

    @Override
    public String toString() {
        return "LieuResponse{" +
                "statue='" + statue + '\'' +
                ", message='" + message + '\'' +
                ", lieu=" + lieu +
                '}';
    }
}
