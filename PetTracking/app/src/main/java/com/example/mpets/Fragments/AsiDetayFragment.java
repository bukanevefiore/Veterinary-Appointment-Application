package com.example.mpets.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mpets.Adapters.SanalKarneGecmisAsiAdapter;
import com.example.mpets.Models.AsiModel;
import com.example.mpets.R;
import com.example.mpets.RestApi.ManagerAll;
import com.example.mpets.Utils.GetSharedPreferences;
import com.example.mpets.Utils.Warnings;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AsiDetayFragment extends Fragment {

   private View view;
   private String musid;
   private String petId;
   private GetSharedPreferences getSharedPreferences;
   private RecyclerView asiDetay_recyclerview;
   private SanalKarneGecmisAsiAdapter sanalKarneGecmisAsiAdapter;
   private List<AsiModel> asiList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_asi_detay, container, false);

        tanimlamalar();
        getGecmisAsiRequest();

        return view;
    }

    public void tanimlamalar(){

        petId=getArguments().getString("petid").toString();
        getSharedPreferences=new GetSharedPreferences(getActivity());
        musid=getSharedPreferences.getSession().getString("id",null);
        asiDetay_recyclerview=view.findViewById(R.id.asiDetay_recyclerview);
        RecyclerView.LayoutManager mng=new GridLayoutManager(getContext(),1);
        asiDetay_recyclerview.setLayoutManager(mng);
        asiList=new ArrayList<>();

    }

    public void getGecmisAsiRequest(){

        Call<List<AsiModel>> req= ManagerAll.getInstance().getGecmisAsi(musid,petId);
        req.enqueue(new Callback<List<AsiModel>>() {
            @Override
            public void onResponse(Call<List<AsiModel>> call, Response<List<AsiModel>> response) {

                Log.i("gecmisasilist",response.body().toString());
                if(response.body().get(0).isTf())
                {

                    asiList=response.body();
                    sanalKarneGecmisAsiAdapter=new SanalKarneGecmisAsiAdapter(asiList,getContext());
                    asiDetay_recyclerview.setAdapter(sanalKarneGecmisAsiAdapter);

                    Toast.makeText(getContext(), "There are " + asiList.size()+" past vaccines for your pet", Toast.LENGTH_LONG).show();
                    
                }else
                {
                    Toast.makeText(getContext(), "You do not have a past vaccine", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AsiModel>> call, Throwable t) {

                Toast.makeText(getContext(), Warnings.internetProblemText, Toast.LENGTH_LONG).show();
                Log.e("asidetayistekhata",t.getMessage());

            }
        });
    }
}