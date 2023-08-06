package com.example.mobile_exploremada.interceptor;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.ui.login.LoginFragment;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private String authToken;
    private FragmentManager fragmentManager;
    private Context context;

    public AuthInterceptor(Context context, String authToken, FragmentManager fragmentManager) {
        this.context = context;
        this.authToken = authToken;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + authToken)
                .build();

        Response response = chain.proceed(newRequest);

        if (response.code() == 401) {
            // Afficher le toast sur le thread principal en utilisant Handler
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    // Vérifier si le fragment est toujours attaché avant de faire des opérations
                    if (context instanceof Activity) {
                        Activity activity = (Activity) context;
                        if (!activity.isFinishing() && !activity.isDestroyed()) {
                            Toast.makeText(activity, "Session expirée. Veuillez vous reconnecter.", Toast.LENGTH_LONG).show();
                            removeTokenFromSharedPreferences();
                            // Rediriger vers le LoginFragment en cas de token invalide ou expiré
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, new LoginFragment())
                                    .commit();
                        }
                    }
                }
            });

            return response;
        }

        return response;
    }


    private void removeTokenFromSharedPreferences() {
        SharedPreferences preferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("token");
        editor.apply();
    }
}
