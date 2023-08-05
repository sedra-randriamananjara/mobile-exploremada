package com.example.mobile_exploremada.utils;

import com.example.mobile_exploremada.models.RegisterModel;
import com.example.mobile_exploremada.request.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("utilisateur/inscription")
    Call<RegisterModel> register(@Body RegisterRequest registerRequest);
}
