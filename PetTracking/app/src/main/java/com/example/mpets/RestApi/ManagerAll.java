package com.example.mpets.RestApi;

import com.example.mpets.Models.LoginModel;
import com.example.mpets.Models.PetModel;
import com.example.mpets.Models.RegisterModel;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private static ManagerAll ourInstance=new ManagerAll();

    public static synchronized ManagerAll getInstance()
    {
        return ourInstance;
    }

    public Call<RegisterModel> kayitOl(String mailAdres, String kadi, String pass)
    {
        Call<RegisterModel> x=getRestApi().registerUser(mailAdres,kadi,pass);
        return x;
    }

    public Call<LoginModel> girisYap(String kadi, String sifre)
    {
        Call<LoginModel> x=getRestApi().loginUser(kadi,sifre);
        return x;
    }

    public Call<List<PetModel>> getPets(String mus_id)
    {
        Call<List<PetModel>> x=getRestApi().getPets(mus_id);
        return x;
    }
}
