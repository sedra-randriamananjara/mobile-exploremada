package com.example.mobile_exploremada.utils;

import com.example.mobile_exploremada.models.LoginModel;
import com.example.mobile_exploremada.request.AuthRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("auth/login")
    Call<LoginModel> authenticate(@Body AuthRequest authRequest);
}
