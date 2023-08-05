package com.example.mobile_exploremada.ui.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.mobile_exploremada.R;

import java.util.Locale;

public class PreferencesFragment extends PreferenceFragmentCompat{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        ListPreference themePreference = findPreference("theme_preference");
        ListPreference languagePreference = findPreference("language_preference");
        if (themePreference != null) {
            themePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    String selectedTheme = (String) newValue;
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit();
                    editor.putString("theme_preference", selectedTheme);
                    editor.apply();

                    //updateViewsBackground(selectedTheme);
                    getActivity().recreate();
                    return true;
                }
            });
        }
        if (languagePreference != null) {
            languagePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    String selectedLanguage = (String) newValue;

                    // Enregistrer la langue sélectionnée dans les préférences
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit();
                    editor.putString("language_preference", selectedLanguage);
                    editor.apply();

                    // Mettre à jour la configuration de la langue
                    Locale locale = new Locale(selectedLanguage);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        config.setLocale(locale);
                    }
                    getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());

                    // Recréez l'activité pour appliquer les changements de langue
                    getActivity().recreate();

                    return true;
                }
            });
        }
    }
}
