package com.java.demers_j.epicture.Entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Data {

    private User userFlickr;
    private User userImgur;
    private Context context;

    public Data(Context context) {
        this.context = context;
        getUserFlickrFromPrefs();
        getUserImgurFromPrefs();
    }

    public User getUserFlickr() {
        return userFlickr;
    }

    public User getUserImgur() {
        return userImgur;
    }

    public User NewFlickrUser(String userName, String password)
    {
        saveUserFlickrToPrefs(new User(userName, password, true));
        return null;
    }

    public User NewImgurUser(String userName, String password)
    {
        saveUserImgurToPrefs(new User(userName, password, true));
        return null;
    }
    
    public boolean getUserFlickrConnected()
    {
        return userFlickr != null && userFlickr.isConnected();
    }

    public boolean getUserImgurConnected()
    {
        return userImgur != null && userImgur.isConnected();
    }

    private void saveUserFlickrToPrefs(User UserFlickr) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(UserFlickr);
        prefsEditor.putString("UserFlickr", json);
        prefsEditor.apply();
    }

    private User getUserFlickrFromPrefs() {
        try {
            SharedPreferences appSharedPrefs = PreferenceManager
                    .getDefaultSharedPreferences(context);
            Gson gson = new Gson();
            String json = appSharedPrefs.getString("UserFlickr", "");
            Type type = new TypeToken<User>() {
            }.getType();
            userFlickr = gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userFlickr;
    }

    private void saveUserImgurToPrefs(User UserImgur) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(UserImgur);
        prefsEditor.putString("UserImgur", json);
        prefsEditor.apply();
    }

    private User getUserImgurFromPrefs() {
        try {
            SharedPreferences appSharedPrefs = PreferenceManager
                    .getDefaultSharedPreferences(context);
            Gson gson = new Gson();
            String json = appSharedPrefs.getString("UserImgur", "");
            Type type = new TypeToken<User>() {
            }.getType();
            userImgur = gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userImgur;
    }

}
