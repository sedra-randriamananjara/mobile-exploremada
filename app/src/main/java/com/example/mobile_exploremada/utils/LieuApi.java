package com.example.mobile_exploremada.utils;

import com.example.mobile_exploremada.response.LieuResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LieuApi {

    //list lieu
    @GET("lieu")
    Call<LieuResponse> findAll();
}
