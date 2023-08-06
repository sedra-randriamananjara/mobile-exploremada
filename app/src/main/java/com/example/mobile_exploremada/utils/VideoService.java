package com.example.mobile_exploremada.utils;

import com.example.mobile_exploremada.response.LieuResponse;
import com.example.mobile_exploremada.response.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VideoService {
    @GET("lieu_video")
    Call<VideoResponse> findAllVideo();
}
