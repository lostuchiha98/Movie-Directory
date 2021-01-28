package com.example.moviedirectory.Util;

import android.app.Activity;
import android.content.SharedPreferences;

public class Sharedpref {
    SharedPreferences sharedPreferences;

    public Sharedpref(Activity activity) {
        sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);
    }

    public void setSearch(String search){
        sharedPreferences.edit().putString("search",search).commit();
    }

    public String getSearch(){
        return sharedPreferences.getString("search","Batman");
    }

}
