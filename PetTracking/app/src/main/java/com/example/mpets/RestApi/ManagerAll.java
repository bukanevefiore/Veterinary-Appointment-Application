package com.example.mpets.RestApi;

import com.example.mpets.Models.AnswersModel;
import com.example.mpets.Models.AskQuestionModel;
import com.example.mpets.Models.CampaignModel;
import com.example.mpets.Models.DeleteAnswerModel;
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

    public Call<AskQuestionModel> soruSor(String id, String soru)
    {
        Call<AskQuestionModel> x=getRestApi().soruSor(id,soru);
        return x;
    }

    public Call<List<AnswersModel>> getAnswers(String mus_id)
    {
        Call<List<AnswersModel>> x=getRestApi().getAnswers(mus_id);
        return x;
    }

    public Call<DeleteAnswerModel> deleteAnswer(String cevapid, String soruid)
    {
        Call<DeleteAnswerModel> x=getRestApi().deleteAnswer(cevapid,soruid);
        return x;
    }

    public Call<List<CampaignModel>> getKampanya()
    {
        Call<List<CampaignModel>> x=getRestApi().getKampanya();
        return x;
    }

}
