package com.example.mpets.RestApi;

import com.example.mpets.Models.LoginPojo;
import com.example.mpets.Models.RegisterPojo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {


    @FormUrlEncoded
    @POST("/veterinary/kaydol.php")
    Call<RegisterPojo> registerUser(@Field("mailAdres") String mailAdres, @Field("kadi") String kadi, @Field("pass") String pass );

    @FormUrlEncoded
    @POST("/veterinary/girisyap.php")
    Call<LoginPojo> loginUser(@Field("kadi") String kadi, @Field("sifre") String sifre );
}
