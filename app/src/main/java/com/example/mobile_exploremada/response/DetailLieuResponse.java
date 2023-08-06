package com.example.mobile_exploremada.response;

import com.example.mobile_exploremada.models.LieuModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailLieuResponse {
    @SerializedName("statue")
    @Expose()
    private String statue;

    @SerializedName("message")
    @Expose()
    private String message;

    @SerializedName("data")
    @Expose()
    private LieuModel lieu;

    public LieuModel getDetailLieu(){
        return lieu;
    }

}
