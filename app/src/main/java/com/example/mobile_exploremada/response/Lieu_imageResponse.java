package com.example.mobile_exploremada.response;
import com.example.mobile_exploremada.models.Lieu_imageModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Lieu_imageResponse{
    @SerializedName("statue")
    @Expose()
    private String statue;

    @SerializedName("message")
    @Expose()
    private String message;

    @SerializedName("data")
    @Expose()
    private List<Lieu_imageModel> lieu_image;

    public List <Lieu_imageModel> getLieu_image(){
        return lieu_image ;
    }

}
