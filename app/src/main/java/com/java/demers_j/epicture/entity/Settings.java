package com.java.demers_j.epicture.entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by demers_j on 18/02/2017.
 */

public class Settings {
    String language;
    Context context;

    public Settings(Context context) {
        this.context = context;
        getUserFlickrFromPrefs();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void saveSettingsToPrefs() {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this.language);
        prefsEditor.putString("Language", json);
        prefsEditor.apply();
    }

    private void getUserFlickrFromPrefs() {
        try {
            SharedPreferences appSharedPrefs = PreferenceManager
                    .getDefaultSharedPreferences(context);
            Gson gson = new Gson();
            String json = appSharedPrefs.getString("Language", "");
            Type type = new TypeToken<String>() {
            }.getType();
            language = gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (language == null)
            language = "en";
    }
}
