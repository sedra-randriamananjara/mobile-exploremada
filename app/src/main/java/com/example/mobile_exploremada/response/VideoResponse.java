package com.example.mobile_exploremada.response;

import com.example.mobile_exploremada.models.LieuModel;
import com.example.mobile_exploremada.models.LieuVideoModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResponse {
    // 1- Find the Lieu
    @SerializedName("statue")
    @Expose()
    private String statue;

    @SerializedName("message")
    @Expose()
    private String message;

    @SerializedName("data")
    @Expose()
    private List<LieuVideoModel> lieu;

    public List<LieuVideoModel> getLieu(){
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
