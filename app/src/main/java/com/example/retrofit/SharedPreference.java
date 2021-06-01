package com.example.retrofit;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.retrofit.ModelResponse.User;

public class SharedPreference {

    private static String SHARED_PREF_NAME = "Riddhiman";
    private SharedPreferences sharedPreference;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPreference(Context context) {
        this.context = context;
    }
    public void SaveUser(User user){
        sharedPreference = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
        editor.putInt("id", user.getId());
        editor.putString("username", user.getUsername());
        editor.putString("email", user.getEmail());
        editor.putBoolean("logged", true);
        editor.apply();
    }

    public boolean isLoggedIn(){

        sharedPreference = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreference.getBoolean("logged",false);
    }

    public User getuser(){
        sharedPreference = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return new User(sharedPreference.getInt("id",-1),
                sharedPreference.getString("username",null),
                sharedPreference.getString("email",null));

    }
    public void logOut(){
        sharedPreference = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
        editor.clear();
        editor.apply();
    }

}
