package com.example.mpets.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpets.Models.PetModel;
import com.example.mpets.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.ViewHolder>{

    private Context context;
    private List<PetModel> list;

    public PetsAdapter(List<PetModel> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.pet_list_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.pet_name.setText("Pet Name : "+list.get(position).petisim.toString());
        holder.pet_tur_name.setText("Pet Kind : "+list.get(position).pettur.toString());
        holder.pet_cins_name.setText("Pet Genus : "+list.get(position).petcins.toString());

        try {
            Picasso.get().load("http://192.168.1.4/veterinary/"+list.get(position).getPetresim()).into(holder.pet_fragment_image);
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
        TextView pet_name,pet_tur_name,pet_cins_name;
        CircleImageView pet_fragment_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pet_name=itemView.findViewById(R.id.pet_name);
            pet_tur_name=itemView.findViewById(R.id.pet_tur_name);
            pet_cins_name=itemView.findViewById(R.id.pet_cins_name);
            pet_fragment_image=itemView.findViewById(R.id.pet_fragment_image);

        }
    }


}
