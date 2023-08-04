package com.example.mobile_exploremada.ui.login;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.helper.NotificationHelper;
import com.example.mobile_exploremada.models.LoginModel;
import com.example.mobile_exploremada.request.AuthRequest;
import com.example.mobile_exploremada.request.Servicey;
import com.example.mobile_exploremada.ui.place.PlaceFragment;
import com.example.mobile_exploremada.utils.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private  Button loginButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Trouver les éléments du layout
        EditText emailEditText = view.findViewById(R.id.editTextEmail);
        EditText passwordEditText = view.findViewById(R.id.editTextPassword);
        loginButton = view.findViewById(R.id.buttonLogin);
        TextView signUpLink = view.findViewById(R.id.textViewSignUpLink);

        // Définir le clic du bouton de connexion
        loginButton.setOnClickListener(v -> {
            // Récupérer les valeurs des champs email et mot de passe
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                traitementLogin(email,password);
            }


        });

        // Définir le clic du lien d'inscription
        signUpLink.setOnClickListener(v -> {
            // Charger le fragment de la page d'inscription
            loadRegistrationFragment();
        });

        return view;
    }

    private void traitementLogin(String email, String motdepasse){
        loginButton.setEnabled(false);
        loginButton.setText("Chargement...");

        AuthRequest authRequest = new AuthRequest(email,motdepasse);
        LoginService api = Servicey.getLoginService();
        Call<LoginModel> call = api.authenticate(authRequest);

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                loginButton.setEnabled(true);
                loginButton.setText("Connexion");
                if (response.isSuccessful()) {
                    LoginModel authResponse = response.body();
                    if (authResponse != null) {
                        if ("ok".equalsIgnoreCase(authResponse.getStatue())) {
                            // Authentification réussie, le token est dans authResponse.getData()
                            String token = authResponse.getData();
                            Toast.makeText(requireContext(), "token : "+token, Toast.LENGTH_SHORT).show();
                            // Faites ce que vous voulez avec le token ici
                            showNotification();
                            loadPlaceFragment();
                        } else {
                            // Afficher le message d'erreur en cas d'échec
                            String errorMessage = authResponse.getMessage();
                            showErrorDialog("Erreur lors de la connexion : " + errorMessage);
                            //Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Code d'erreur HTTP, gérer les erreurs
                    //Toast.makeText(requireContext(), "Erreur lors de la connexion", Toast.LENGTH_SHORT).show();
                    showErrorDialog("Une erreur s'est produite : Votre e-mail ou mot de passe incorrecte");
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                loginButton.setEnabled(true);
                loginButton.setText("Connexion");
                //Toast.makeText(requireContext(), "Erreur réseau", Toast.LENGTH_SHORT).show();
                showErrorDialog("Erreur réseau : Verifier a ce que votre connexion marche");
            }
        });
    }
    private void showErrorDialog(String errorMessage) {
        Dialog errorDialog = new Dialog(requireContext());
        errorDialog.setContentView(R.layout.dialog_error);
        TextView textViewErrorMessage = errorDialog.findViewById(R.id.textViewErrorMessage);
        Button buttonDismiss = errorDialog.findViewById(R.id.buttonDismiss);
        textViewErrorMessage.setText(errorMessage);
        buttonDismiss.setOnClickListener(v -> errorDialog.dismiss());
        errorDialog.show();
    }
    private void loadPlaceFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlaceFragment placeFragment = new PlaceFragment();
        fragmentTransaction.replace(R.id.fragment_container, placeFragment);
        fragmentTransaction.commit();
    }
    private void loadRegistrationFragment() {
        // Charger le fragment de la page d'inscription
        // Utilisez la même méthode loadRegistrationFragment() que dans l'exemple précédent
    }

    private void showNotification() {
        String title = "Allons decouvrir Madagascar";
        String text = "Bonjour, Bienvenue parmis nous.";

        // Show the notification using NotificationHelper
        NotificationHelper.showNotification(requireContext(), title, text);
    }
}
