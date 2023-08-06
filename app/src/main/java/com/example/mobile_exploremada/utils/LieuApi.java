package com.example.mobile_exploremada.utils;

import com.example.mobile_exploremada.response.DetailLieuResponse;
import com.example.mobile_exploremada.response.LieuResponse;
import com.example.mobile_exploremada.response.Lieu_imageResponse;
import com.example.mobile_exploremada.response.Lieu_videoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LieuApi {

    @GET("lieu")
    Call<LieuResponse> findAll();

    @GET("lieu")
    Call<LieuResponse> chercherLieu(@Query("q") String recherche);

    @GET("lieu/{id}")
    Call<DetailLieuResponse> findLieuById(@Path("id") int id);

    @GET("lieu_image/")
    Call<Lieu_imageResponse> getLieu_image();


    @GET("lieu_video/")
    Call<Lieu_videoResponse> getLieu_video();

}


