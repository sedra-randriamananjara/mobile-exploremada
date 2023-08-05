package com.example.mobile_exploremada.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static final String PREF_NAME = "MyPrefs";
    private static final String TOKEN_KEY = "token";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getToken(Context context) {
        return getSharedPreferences(context).getString(TOKEN_KEY, "");
    }

    public static void saveToken(Context context, String token) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }
}
