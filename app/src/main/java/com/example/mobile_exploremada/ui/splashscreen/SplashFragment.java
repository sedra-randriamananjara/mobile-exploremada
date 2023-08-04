package com.example.mobile_exploremada.ui.splashscreen;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.ui.login.LoginFragment;


public class SplashFragment extends Fragment {
    ImageView logo,appName,splashImg;
    LottieAnimationView lottieAnimationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        logo = view.findViewById(R.id.logo);
        appName = view.findViewById(R.id.name);
        splashImg = view.findViewById(R.id.img);
        lottieAnimationView = view.findViewById(R.id.lottie);


        splashImg.animate().translationY(-1600).setDuration(8000).setStartDelay(8000);
        logo.animate().translationY(1400).setDuration(8000).setStartDelay(8000);
        appName.animate().translationY(1400).setDuration(8000).setStartDelay(8000);
        lottieAnimationView.setRepeatCount(ValueAnimator.INFINITE);




        new Handler().postDelayed(this::loadLoginFragment, 8000);
        return view;
    }

    private void loadLoginFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment.class,null)
                .commit();
    }
}

