package com.example.lukile.toogoodtothrow.model;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveUserDataPreferences {

    public static String PREF_EMAIL; // nom du fichier
    private static String TOKEN_KEY;
    private static SharedPreferences prefs;

    public SaveUserDataPreferences(String email) {
        PREF_EMAIL = email;
    }

    public static String getToken(Context c) {
        prefs = c.getSharedPreferences(PREF_EMAIL, Context.MODE_PRIVATE); // MODE_... = mode de fonctionnement
        // MODE_PRIVATE : le mode par défaut, où le fichier créé n'est accessible que par l'application appelante
        return prefs.getString(TOKEN_KEY, "");
    }

    public static String getIdUser(Context c) {
        prefs = c.getSharedPreferences(PREF_EMAIL, Context.MODE_PRIVATE); // MODE_... = mode de fonctionnement
        // MODE_PRIVATE : le mode par défaut, où le fichier créé n'est accessible que par l'application appelante
        return prefs.getString("userID", "");
    }


        public static void setToken(Context c, String token) {
        prefs = c.getSharedPreferences(PREF_EMAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public static void setId(Context c, int id) {
        prefs = c.getSharedPreferences(PREF_EMAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("userID", id);
        editor.apply();
    }
}
