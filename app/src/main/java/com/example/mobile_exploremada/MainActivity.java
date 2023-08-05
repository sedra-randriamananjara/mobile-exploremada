package com.example.mobile_exploremada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.mobile_exploremada.ui.splashscreen.SplashFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String themePreference = sharedPreferences.getString("theme_preference", "");
        if ("light".equals(themePreference)) {
            setTheme(R.style.Theme_Mobileexploremada);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        } else if ("dark".equals(themePreference)) {
            setTheme(R.style.Base_Theme_BottomNav_Night);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadSplashFragment();
    }

    private void loadSplashFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SplashFragment splashFragment = new SplashFragment();
        fragmentTransaction.replace(R.id.fragment_container, splashFragment);
        fragmentTransaction.commit();
    }
}