package com.example.mobile_exploremada.utils;

import com.example.mobile_exploremada.models.LoginModel;
import com.example.mobile_exploremada.models.MeModel;
import com.example.mobile_exploremada.models.UserModel;
import com.example.mobile_exploremada.request.AuthRequest;
import com.example.mobile_exploremada.response.MeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginService {
    @POST("auth/login")
    Call<LoginModel> authenticate(@Body AuthRequest authRequest);

    @GET("auth/me")
    Call<MeResponse> getUserData();
}
