package com.example.mobile_exploremada.response;

import com.example.mobile_exploremada.models.Lieu_videoModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Lieu_videoResponse
{

    @SerializedName("statue")
    @Expose()
    private String statue;

    @SerializedName("message")
    @Expose()
    private String message;

    @SerializedName("data")
    @Expose()
    private List<Lieu_videoModel> lieu_video;
    public List <Lieu_videoModel> getLieu_video(){
        return lieu_video ;
    }

}
