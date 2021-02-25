package com.example.mpets.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mpets.Adapters.CampaignAdapter;
import com.example.mpets.Models.CampaignModel;
import com.example.mpets.R;
import com.example.mpets.RestApi.ManagerAll;
import com.example.mpets.Utils.Warnings;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CampaignFragment extends Fragment {

    private View view;
    private RecyclerView campaign_recyclerview;
    CampaignAdapter campaignAdapter;
    List<CampaignModel> campaignList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_campaign, container, false);

        tanimlamalar();
        kampanyaRequest();

        return view;
    }

    public void tanimlamalar(){

        campaign_recyclerview=view.findViewById(R.id.campaign_recyclerview);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        campaign_recyclerview.setLayoutManager(layoutManager);
        campaignList=new ArrayList<>();

    }

    public void kampanyaRequest(){

        Call<List<CampaignModel>> request= ManagerAll.getInstance().getKampanya();
        request.enqueue(new Callback<List<CampaignModel>>() {
            @Override
            public void onResponse(Call<List<CampaignModel>> call, Response<List<CampaignModel>> response) {

                if(response.body().get(0).isTf()){

                    Log.i("kampanyaliste",response.body().toString());
                    campaignList=response.body();
                    campaignAdapter=new CampaignAdapter(campaignList,getContext());
                    campaign_recyclerview.setAdapter(campaignAdapter);
                    Toast.makeText(getContext(), "We have "+response.body().size()+" campaign", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(getContext(), "There is no campaign", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<List<CampaignModel>> call, Throwable t) {

                Toast.makeText(getContext(), Warnings.internetProblemText, Toast.LENGTH_LONG).show();
                Log.e("campaign",t.getMessage());

            }
        });
    }
}