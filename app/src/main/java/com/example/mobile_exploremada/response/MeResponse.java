package com.example.mobile_exploremada.response;

import com.example.mobile_exploremada.models.LieuModel;
import com.example.mobile_exploremada.models.MeModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MeResponse {
    // 1- Find the Lieu
    @SerializedName("statue")
    @Expose()
    private String statue;

    @SerializedName("message")
    @Expose()
    private String message;

    @SerializedName("data")
    @Expose()
    private MeModel me;

    public MeModel getMe(){
        return me;
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

    @Override
    public String toString() {
        return "LieuResponse{" +
                "statue='" + statue + '\'' +
                ", message='" + message + '\'' +
                ", me=" + me +
                '}';
    }
}
