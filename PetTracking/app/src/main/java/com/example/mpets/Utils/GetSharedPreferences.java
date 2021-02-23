package com.example.mpets.Utils;

import android.app.Activity;
import android.content.SharedPreferences;

/* uygulamada 1 kere login olduktan çıkış yapmadığımız sürece tekrar giriş yapmayı engellemek için
 oluşturulan shared preferences sınıfı  */
public class GetSharedPreferences {

    private SharedPreferences sharedPreferences;
    Activity activity;

    public GetSharedPreferences(Activity activity) {
        this.activity = activity;

    }

    public SharedPreferences getSession(){

        sharedPreferences=activity.getApplicationContext().getSharedPreferences("session",0);
        return sharedPreferences;
    }

    // bilgileri set etme
    public void setSession(String id,String username,String mailAdres){

        sharedPreferences=activity.getApplicationContext().getSharedPreferences("session",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("id",id);
        editor.putString("username",username);
        editor.putString("mailAdres",mailAdres);
        editor.commit();  // kalıcı giriş
    }
}
