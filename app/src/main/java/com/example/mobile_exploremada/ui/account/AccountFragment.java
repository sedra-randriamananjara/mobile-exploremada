package com.example.mobile_exploremada.ui.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.models.UserData;
import com.example.mobile_exploremada.models.UserModel;
import com.example.mobile_exploremada.request.Servicey;
import com.example.mobile_exploremada.ui.login.LoginFragment;
import com.example.mobile_exploremada.ui.preference.PreferencesFragment;
import com.example.mobile_exploremada.utils.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TextView userDataTextView = view.findViewById(R.id.userDataTextView);
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

        String authToken = getTokenFromSharedPreferences();

        Toast.makeText(requireContext(), authToken, Toast.LENGTH_SHORT).show();
        // Enregistrer l'intercepteur avec le token d'authentification
        Servicey.addAuthInterceptor(requireContext(),authToken,requireActivity().getSupportFragmentManager());

        LoginService api = Servicey.getLoginService();
        Call<UserModel> call = api.getUserData();

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel userData = response.body();
                    if (userData != null && "ok".equalsIgnoreCase(userData.getStatue())) {
                        // Afficher les données de l'utilisateur dans le fragment
                        String message = userData.getMessage();
                        int data = userData.getData();
                        //Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show();
                        String userDataStr = "Message: " + message + "Data: " + data;
//                        Log.v("Tag", userDataStr);
                        //Toast.makeText(requireContext(), userDataStr, Toast.LENGTH_SHORT).show();

                        //Log.d("LocationFragment", "Updating TextView with userDataStr: " + userDataStr);
                        userDataTextView.setText(userDataStr);
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
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                // Afficher un message d'erreur en cas d'échec de la requête
                String errorMessage = "Erreur réseau : " + t.toString();
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
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
