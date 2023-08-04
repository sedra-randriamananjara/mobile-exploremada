package com.example.mobile_exploremada.request;

import com.example.mobile_exploremada.utils.Credentials;
import com.example.mobile_exploremada.utils.LieuApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicey {
    
    private static Retrofit.Builder retrofitBuilder = 
            new Retrofit.Builder()
                    .baseUrl(Credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static LieuApi lieuApi = retrofit.create(LieuApi.class);

    public static LieuApi getLieuApi(){
        return lieuApi;
    }
}
