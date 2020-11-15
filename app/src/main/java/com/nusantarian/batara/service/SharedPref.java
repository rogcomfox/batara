package com.nusantarian.batara.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.nusantarian.batara.model.User;

public class SharedPref {
    private static final String SHARED_PREF = "my_shared_pref";
    private static SharedPref mInstance;
    private Context mContext;

    public SharedPref(Context mContext){
        this.mContext = mContext;
    }

    public static synchronized SharedPref getInstance(Context mContext){
        if (mInstance == null){
            mInstance = new SharedPref(mContext);
        }
        return mInstance;
    }

    public void saveToken(String token){
        SharedPreferences sp = mContext.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("token", token);
        editor.putInt("isLoggedIn", 1);
        editor.apply();
    }

    public String getToken(){
        SharedPreferences sp = mContext.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        return sp.getString("token", null);
    }

    public void saveUser(User user){
        SharedPreferences sp = mContext.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("id", user.getId());
        editor.putString("fullName", user.getFullName());
        editor.putString("username", user.getUsername());
        editor.putString("email", user.getEmail());
        editor.putString("password", user.getPassword());
        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sp = mContext.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        return sp.getInt("isLoggedIn", -1) != -1;
    }

    public User getUser(){
        SharedPreferences sp = mContext.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        return new User(
                sp.getString("id", null),
                sp.getString("fullName", null),
                sp.getString("username", null),
                sp.getString("email", null),
                sp.getString("password", null)
        );
    }

    public void clear(){
        SharedPreferences sp = mContext.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
