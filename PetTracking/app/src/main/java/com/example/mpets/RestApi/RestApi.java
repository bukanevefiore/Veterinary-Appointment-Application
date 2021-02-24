package com.example.mpets.RestApi;

import com.example.mpets.Models.LoginModel;
import com.example.mpets.Models.PetModel;
import com.example.mpets.Models.RegisterModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {


    @FormUrlEncoded
    @POST("/veterinary/kaydol.php")
    Call<RegisterModel> registerUser(@Field("mailAdres") String mailAdres, @Field("kadi") String kadi, @Field("pass") String pass );

    @FormUrlEncoded
    @POST("/veterinary/girisyap.php")
    Call<LoginModel> loginUser(@Field("kadi") String kadi, @Field("sifre") String sifre );

    @FormUrlEncoded
    @POST("/veterinary/petlerim.php")
    Call<List<PetModel>> getPets(@Field("musid") String mus_id);
}
