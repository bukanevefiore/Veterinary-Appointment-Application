package com.example.mpets.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mpets.Models.AskQuestionModel;
import com.example.mpets.R;
import com.example.mpets.RestApi.ManagerAll;
import com.example.mpets.Utils.ChangeFragments;
import com.example.mpets.Utils.GetSharedPreferences;
import com.example.mpets.Utils.Warnings;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private View view;
    CardView card_view_myPets,card_view_karne,card_view_asılar,card_view_soru,card_view_cevaplar,card_view_kampanya,
            card_view_iletisim;
    ChangeFragments changeFragments;
    private GetSharedPreferences getSharedPreferences;
    private String id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        tanimlamalar();
        action();

        return view;
    }
    public void tanimlamalar(){

        changeFragments=new ChangeFragments(getContext());
        card_view_myPets=view.findViewById(R.id.card_view_myPets);
        card_view_karne=view.findViewById(R.id.card_view_karne);
        card_view_asılar=view.findViewById(R.id.card_view_asılar);
        card_view_soru=view.findViewById(R.id.card_view_soru);
        card_view_cevaplar=view.findViewById(R.id.card_view_cevaplar);
        card_view_kampanya=view.findViewById(R.id.card_view_kampanya);
        card_view_iletisim=view.findViewById(R.id.card_view_iletisim);
        getSharedPreferences=new GetSharedPreferences(getActivity());
        id=getSharedPreferences.getSession().getString("id",null);

    }

    public void action(){

        card_view_myPets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeFragments.change(new UserPetsFragment());

            }
        });
        card_view_karne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        card_view_asılar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        card_view_soru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openQuestionAlert();

            }
        });
        card_view_cevaplar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        card_view_kampanya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        card_view_iletisim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    // soru sora tıklanınca açılacak alert diyolog
    public void openQuestionAlert(){

        LayoutInflater layoutInflater=this.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sorusor_alert_layout,null);

        final TextInputEditText sorusorEditText;
        Button sorusorButon;

        sorusorEditText=view.findViewById(R.id.sorusorEditText);
        sorusorButon=view.findViewById(R.id.sorusorButon);

        // alert diyolog tetikleme
        AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
        alert.setView(view);
        // ekranın herhangi bir yerine tıklayınca alert diyoloğun kaybolması için:
        alert.setCancelable(true);
        AlertDialog alertDialog=alert.create(); // alert diyoloğu nesne üreterek oluşturma

        sorusorButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String soru=sorusorEditText.getText().toString();
                sorusorEditText.setText("");
                alertDialog.cancel();
                askQuestionRequest(id,soru,alertDialog);
            }
        });

        alertDialog.show();
    }

    public void askQuestionRequest(String mus_id,String text,AlertDialog alr){

        Call<AskQuestionModel> request= ManagerAll.getInstance().soruSor(mus_id,text);
        request.enqueue(new Callback<AskQuestionModel>() {
            @Override
            public void onResponse(Call<AskQuestionModel> call, Response<AskQuestionModel> response) {

                if(response.body().isTf()){

                    Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_SHORT).show();
                    alr.cancel();

                }else{

                    Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AskQuestionModel> call, Throwable t) {

                Log.e("sorupushhata",t.getMessage());
                Toast.makeText(getContext(), Warnings.internetProblemText, Toast.LENGTH_SHORT).show();

            }
        });

    }

}