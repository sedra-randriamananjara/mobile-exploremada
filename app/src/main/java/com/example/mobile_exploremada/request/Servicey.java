package com.example.mobile_exploremada.request;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.example.mobile_exploremada.interceptor.AuthInterceptor;
import com.example.mobile_exploremada.utils.Credentials;
import com.example.mobile_exploremada.utils.LieuApi;
import com.example.mobile_exploremada.utils.LoginService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;

public class Servicey {
    
    private static Retrofit.Builder retrofitBuilder = 
            new Retrofit.Builder()
                    .baseUrl(Credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();
    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    private static LieuApi lieuApi = retrofit.create(LieuApi.class);

    public static LieuApi getLieuApi(){
        return lieuApi;
    }

    public static LoginService getLoginService(){
        return retrofit.create(LoginService.class);
    }

    public static void addAuthInterceptor(Context context, String authToken, FragmentManager fragmentManager) {
        AuthInterceptor interceptor = new AuthInterceptor(context, authToken, fragmentManager);
        httpClientBuilder.interceptors().clear();
        httpClientBuilder.addInterceptor(interceptor);
        retrofitBuilder.client(httpClientBuilder.build());
        retrofit = retrofitBuilder.build();
    }
}
