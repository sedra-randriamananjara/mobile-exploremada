package com.example.mobile_exploremada.utils;

import com.example.mobile_exploremada.models.ImageModel;
import com.example.mobile_exploremada.response.ImageResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImageService {
    @GET("/explore")
    Call<ImageResponse> getImages();
}
