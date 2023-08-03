package com.example.mobile_exploremada.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.ui.place.PlaceFragment;

public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Trouver les éléments du layout
        EditText emailEditText = view.findViewById(R.id.editTextEmail);
        EditText passwordEditText = view.findViewById(R.id.editTextPassword);
        Button loginButton = view.findViewById(R.id.buttonLogin);
        TextView signUpLink = view.findViewById(R.id.textViewSignUpLink);

        // Définir le clic du bouton de connexion
        loginButton.setOnClickListener(v -> {
            // Récupérer les valeurs des champs email et mot de passe
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Ici, vous pouvez ajouter la logique pour vérifier les informations de connexion
            // par exemple, effectuer une requête HTTP vers un serveur pour authentifier l'utilisateur.

            // Pour le moment, nous allons simplement afficher un message de connexion réussie.
            Toast.makeText(requireContext(), "Connexion réussie pour : " + email, Toast.LENGTH_SHORT).show();

            loadPlaceFragment();
        });

        // Définir le clic du lien d'inscription
        signUpLink.setOnClickListener(v -> {
            // Charger le fragment de la page d'inscription
            loadRegistrationFragment();
        });

        return view;
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
}
