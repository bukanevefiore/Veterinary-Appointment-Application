package com.example.mpets.Fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mpets.Adapters.PetsAdapter;
import com.example.mpets.Models.PetModel;
import com.example.mpets.R;
import com.example.mpets.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserPetsFragment extends Fragment {

    private View view;
    List<PetModel> petList;
    PetsAdapter petsAdapter;
    RecyclerView pets_recyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_user_pets, container, false);

        tanimlamalar();
        getPets("1");

        return view;
    }

    public void tanimlamalar(){


        petList=new ArrayList<>();
        pets_recyclerview=view.findViewById(R.id.pets_recyclerview);
        RecyclerView.LayoutManager mng=new LinearLayoutManager(getContext());
        pets_recyclerview.setLayoutManager(mng);



    }

    public void getPets(String mus_id){

        Call<List<PetModel>> request= ManagerAll.getInstance().getPets(mus_id);
        request.enqueue(new Callback<List<PetModel>>() {
            @Override
            public void onResponse(Call<List<PetModel>> call, Response<List<PetModel>> response) {

                if(response.body().get(0).isTf())
                {
                    Log.i("aaa",response.body().toString());
                    petList=response.body();
                    petsAdapter=new PetsAdapter(getContext(),petList);
                    pets_recyclerview.setAdapter(petsAdapter);
                    Toast.makeText(getContext(), "You have"+petList.size()+ "pets in the system\n", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getContext(), "You do not have pets registered !!\n", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<PetModel>> call, Throwable t) {

                Log.e("petkontrol",t.getMessage());
            }
        });
    }
}