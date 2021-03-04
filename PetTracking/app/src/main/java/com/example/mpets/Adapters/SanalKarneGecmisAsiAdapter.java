package com.example.mpets.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpets.Models.AsiModel;
import com.example.mpets.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SanalKarneGecmisAsiAdapter extends RecyclerView.Adapter<SanalKarneGecmisAsiAdapter.ViewHolder>{

    private Context context;
    private List<AsiModel> list;

    public SanalKarneGecmisAsiAdapter(List<AsiModel> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.sanal_karne_gecmis_asi_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            holder.sanalKarneGecmisAsiText.setText(list.get(position).getAsiisim().toString()+" Vaccinated");
            holder.sanalKarneGecmisAsiBilgi.setText(list.get(position).pettur.toString() + " type of " + list.get(position)
                    .getPetcins().toString() + " to your pet" +list.get(position).getAsitarih().toString()+" in the history of "+list.get(position).getAsiisim()+
                    " vaccinated.");
        }catch (Exception e){
            Log.e("sanalkarnekont",e.getMessage());

        }

        try {
            Picasso.get().load("http://localhost/veterinary/"+list.get(position).getPetresim()).into(holder.sanalKarneGecmiasAsiImagee);
        }catch(Exception e){
            Log.e("resimhata",e.getMessage());
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // view elemanlarını tanımlamak için bir iner class oluştuuryoruz
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView sanalKarneGecmisAsiText,sanalKarneGecmisAsiBilgi;
        CircleImageView sanalKarneGecmiasAsiImagee;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sanalKarneGecmisAsiText=itemView.findViewById(R.id.sanalKarneGecmisAsiText);
            sanalKarneGecmisAsiBilgi=itemView.findViewById(R.id.sanalKarneGecmisAsiBilgi);
            sanalKarneGecmiasAsiImagee=itemView.findViewById(R.id.sanalKarneGecmiasAsiImagee);


        }
    }


}
