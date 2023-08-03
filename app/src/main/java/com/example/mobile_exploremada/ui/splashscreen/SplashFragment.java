package com.example.mobile_exploremada.ui.splashscreen;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.ui.login.LoginFragment;


public class SplashFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        new Handler().postDelayed(this::loadLoginFragment, 3000);

        return view;
    }

    private void loadLoginFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment.class,null)
                .commit();
    }
}

