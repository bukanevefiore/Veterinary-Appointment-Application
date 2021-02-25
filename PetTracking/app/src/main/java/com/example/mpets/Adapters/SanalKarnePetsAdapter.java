package com.example.mpets.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpets.Models.PetModel;
import com.example.mpets.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SanalKarnePetsAdapter extends RecyclerView.Adapter<SanalKarnePetsAdapter.ViewHolder>{

    private Context context;
    private List<PetModel> list;

    public SanalKarnePetsAdapter(List<PetModel> list, Context context) {
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

        holder.sanalKarnePetText.setText(list.get(position).getPetisim().toString());
        holder.sanalKarnePetBilgi.setText(list.get(position).pettur.toString()+" type of "+list.get(position)
        .getPetcins().toString()+" Click to see past vaccines for the breed");


        try {
            Picasso.get().load("http://192.168.1.4/veterinary/"+list.get(position).getPetresim()).into(holder.sanalKarnePetImage);
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
        TextView sanalKarnePetText,sanalKarnePetBilgi;
        CircleImageView sanalKarnePetImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sanalKarnePetText=itemView.findViewById(R.id.sanalKarnePetText);
            sanalKarnePetBilgi=itemView.findViewById(R.id.sanalKarnePetBilgi);
            sanalKarnePetImage=itemView.findViewById(R.id.sanalKarnePetImage);

        }
    }


}
