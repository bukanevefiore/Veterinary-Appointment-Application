package com.example.mpets.RestApi;

import com.example.mpets.Models.AnswersModel;
import com.example.mpets.Models.AsiModel;
import com.example.mpets.Models.AskQuestionModel;
import com.example.mpets.Models.CampaignModel;
import com.example.mpets.Models.DeleteAnswerModel;
import com.example.mpets.Models.LoginModel;
import com.example.mpets.Models.PetModel;
import com.example.mpets.Models.RegisterModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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

    @FormUrlEncoded
    @POST("/veterinary/sorusor.php")
    Call<AskQuestionModel> soruSor(@Field("id") String mus_id, @Field("soru") String soru);

    @FormUrlEncoded
    @POST("/veterinary/cevaplar.php")
    Call<List<AnswersModel>> getAnswers(@Field("mus_id") String mus_id);

    @FormUrlEncoded
    @POST("/veterinary/cevapsil.php")
    Call<DeleteAnswerModel> deleteAnswer(@Field("cevapid") String cevapid, @Field("soruid") String soruid);

    @GET("/veterinary/kampanya.php")
    Call<List<CampaignModel>> getKampanya();

    @FormUrlEncoded
    @POST("/veterinary/asitakip.php")
    Call<List<AsiModel>> getAsi(@Field("id") String id);


}
