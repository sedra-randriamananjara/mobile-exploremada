package com.example.mobile_exploremada.ui.splashscreen.login.ui.login;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.models.RegisterModel;
import com.example.mobile_exploremada.request.RegisterRequest;
import com.example.mobile_exploremada.request.Servicey;
import com.example.mobile_exploremada.ui.login.LoginFragment;
import com.example.mobile_exploremada.utils.RegisterService;

import java.io.IOException;

import retrofit2.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    private Button registerButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Trouver les éléments du layout
        EditText nameEditText = view.findViewById(R.id.editTextName);
        EditText emailEditText = view.findViewById(R.id.editTextEmailSignup);
        EditText passwordEditText = view.findViewById(R.id.editTextPasswordSignup);
        EditText confirmPasswordEditText = view.findViewById(R.id.editTextConfirmPasswordSignup);
        EditText contactEditText = view.findViewById(R.id.editTextContact);
        registerButton = view.findViewById(R.id.buttonSignup);
        TextView loginLink = view.findViewById(R.id.textViewLoginLink);

        // Définir le clic du bouton d'inscription
        registerButton.setOnClickListener(v -> {
            // Récupérer les valeurs des champs d'inscription
            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();
            String contact = contactEditText.getText().toString();

            // Effectuer les vérifications et le traitement de l'inscription
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || contact.isEmpty()) {
                Toast.makeText(requireContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(requireContext(), "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            } else {
                processRegistration(name, email, password, confirmPassword, contact);
            }
        });

        // Définir le clic du lien de connexion
        loginLink.setOnClickListener(v -> {
            // Charger le fragment de la page de connexion
            loadLoginFragment();
        });
    }



    private void processRegistration(String name, String email, String password,String confirmPassword, String contact) {
        registerButton.setEnabled(false);
        registerButton.setText("Chargement...");

        // Utiliser l'API d'inscription pour envoyer la demande
        RegisterRequest registerRequest = new RegisterRequest( name,  email,password, confirmPassword, contact);
        RegisterService registerService = Servicey.getRegisterService();
        Call<RegisterModel> call = registerService.register(registerRequest);
        Log.d("RegisterFragment", call.toString());
        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                registerButton.setEnabled(true);
                registerButton.setText("Register");
                Log.d("RegisterFragment", "Response code: " + response.code());
                if (response.isSuccessful()) {
                    RegisterModel signupResponse = response.body();
                    if (signupResponse != null) {
                        if ("ok".equalsIgnoreCase(signupResponse.getStatue())) {
                            Toast.makeText(requireContext(), "Inscription réussie", Toast.LENGTH_SHORT).show();
                            loadLoginFragment();
                        } else {
                            String errorMessage = signupResponse.getMessage();
                            showErrorDialog("Erreur lors de l'inscription : " + signupResponse.getData()+"+++"+ signupResponse.getMessage());
                        }
                    }
                } else {
                    try {
                        String errorResponse = response.errorBody().string();
                        Log.e("SignupFragment", "Erreur de réponse : " + errorResponse);
                        showErrorDialog("Erreur lors de l'inscription : "+errorResponse);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                registerButton.setEnabled(true);
                registerButton.setText("Register");
                showErrorDialog("Erreur réseau : Veuillez vérifier votre connexion");

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
    private void loadLoginFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.replace(R.id.fragment_container, loginFragment);
        fragmentTransaction.commit();
    }
}
