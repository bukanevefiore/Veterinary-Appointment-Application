package com.example.mpets.RestApi;

import com.example.mpets.Models.RegisterPojo;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private static ManagerAll ourInstance=new ManagerAll();

    public static synchronized ManagerAll getInstance()
    {
        return ourInstance;
    }

    public Call<RegisterPojo> kayitOl(String mailAdres, String kadi, String pass)
    {
        Call<RegisterPojo> x=getRestApi().registerUser(mailAdres,kadi,pass);
        return x;
    }
}
