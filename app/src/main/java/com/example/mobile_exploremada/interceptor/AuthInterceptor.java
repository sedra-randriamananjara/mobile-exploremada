package com.example.mobile_exploremada.interceptor;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

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
            // Rediriger vers le LoginFragment en cas de token invalide ou expiré
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
            // Afficher un message d'erreur pour informer l'utilisateur de la déconnexion
            Toast.makeText(context, "Session expirée. Veuillez vous reconnecter.", Toast.LENGTH_LONG).show();
        }

        return response;
    }
}
