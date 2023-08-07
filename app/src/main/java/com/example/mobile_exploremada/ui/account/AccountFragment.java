package com.example.mobile_exploremada.ui.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.models.MeModel;
import com.example.mobile_exploremada.models.UserData;
import com.example.mobile_exploremada.models.UserModel;
import com.example.mobile_exploremada.request.Servicey;
import com.example.mobile_exploremada.response.MeResponse;
import com.example.mobile_exploremada.ui.login.LoginFragment;
import com.example.mobile_exploremada.ui.preference.PreferencesFragment;
import com.example.mobile_exploremada.utils.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {

    private ProgressBar progressBar;

    TextView userDataEmail,userDataContact;
            
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TextView userDataTextView = view.findViewById(R.id.userDataTextView);
        userDataEmail = view.findViewById(R.id.userDataEmail);
        userDataContact = view.findViewById(R.id.userDataContact);
        progressBar = view.findViewById(R.id.progressBar);
        Button btnPreferences = view.findViewById(R.id.btn_preferences);
        Button btnLogout = view.findViewById(R.id.btn_logout);
        fetchUserData(userDataTextView);

        btnPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers PreferencesFragment lors du clic sur le bouton
                PreferencesFragment preferencesFragment = new PreferencesFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, preferencesFragment);
                transaction.addToBackStack(null); // Permet de revenir au fragment précédent en appuyant sur le bouton de retour
                transaction.commit();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Appeler la méthode pour déconnecter l'utilisateur
                logoutUser();
            }
        });

        return view;
    }
    private String getTokenFromSharedPreferences() {
        SharedPreferences preferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return preferences.getString("token", "");
    }
    private void fetchUserData(TextView userDataTextView) {
        progressBar.setVisibility(View.VISIBLE);

        String authToken = getTokenFromSharedPreferences();

        //Toast.makeText(requireContext(), authToken, Toast.LENGTH_SHORT).show();
        // Enregistrer l'intercepteur avec le token d'authentification
        Servicey.addAuthInterceptor(requireContext(),authToken,requireActivity().getSupportFragmentManager());

        LoginService api = Servicey.getLoginService();
        Call<MeResponse> call = api.getUserData();

        call.enqueue(new Callback<MeResponse>() {
            @Override
            public void onResponse(Call<MeResponse> call, Response<MeResponse> response) {
                if (response.isSuccessful()) {
                    MeResponse userData = response.body();
                    if (userData != null && "ok".equalsIgnoreCase(response.body().getStatue())) {
                        // Afficher les données de l'utilisateur dans le fragment
                        MeModel me = userData.getMe();
                        //Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show();
//                        Log.v("Tag", userDataStr);
                        //Toast.makeText(requireContext(), userDataStr, Toast.LENGTH_SHORT).show();

                        //Log.d("LocationFragment", "Updating TextView with userDataStr: " + userDataStr);
                        userDataTextView.setText(me.getNom());
                        userDataEmail.setText(me.getEmail());
                        userDataContact.setText(me.getContact());
                    } else {
                        // Afficher un message d'erreur en cas d'échec
                        String errorMessage = "Erreur lors de la récupération des données de l'utilisateur.";
                        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Afficher un message d'erreur en cas d'échec
                    String errorMessage = "Erreur lors de la récupération des données de l'utilisateur.";
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MeResponse> call, Throwable t) {
                // Afficher un message d'erreur en cas d'échec de la requête
                String errorMessage = "Erreur réseau : " + t.toString();
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void logoutUser() {
        // Supprimer le token des préférences partagées
        removeTokenFromSharedPreferences();

        // Rediriger vers le fragment de connexion (LoginFragment)
        loadLoginFragment();
    }

    private void removeTokenFromSharedPreferences() {
        SharedPreferences preferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("token");
        editor.apply();
    }
    private void loadLoginFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.replace(R.id.fragment_container, loginFragment);
        fragmentTransaction.commit();
    }
}
