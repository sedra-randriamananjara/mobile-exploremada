package com.example.mobile_exploremada.ui.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.mobile_exploremada.R;

public class PreferencesFragment extends PreferenceFragmentCompat{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        ListPreference themePreference = findPreference("theme_preference");
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
    }
}
