package com.example.mpets.Fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mpets.R;
import com.example.mpets.Utils.ChangeFragments;


public class HomeFragment extends Fragment {

    private View view;
    CardView card_view_myPets,card_view_karne,card_view_asılar,card_view_soru,card_view_cevaplar,card_view_kampanya,
            card_view_iletisim;
    ChangeFragments changeFragments;


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
}