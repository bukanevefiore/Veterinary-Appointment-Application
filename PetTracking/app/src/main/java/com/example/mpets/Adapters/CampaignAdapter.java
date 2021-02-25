package com.example.mpets.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpets.Models.AnswersModel;
import com.example.mpets.Models.CampaignModel;
import com.example.mpets.Models.DeleteAnswerModel;
import com.example.mpets.R;
import com.example.mpets.RestApi.ManagerAll;
import com.example.mpets.Utils.Warnings;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.ViewHolder>{

    private Context context;
    private List<CampaignModel> list;

    public CampaignAdapter(List<CampaignModel> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.kampanya_view_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.kampanya_baslik.setText(list.get(position).getBaslik().toString());
        holder.kampanya_text.setText(list.get(position).getText().toString());

        Picasso.get().load("http://192.168.1.4/veterinary/" +list.get(position).getResim()).into(holder.kampanya_image);

       
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    // view elemanlarını tanımlamak için bir iner class oluştuuryoruz
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView kampanya_baslik,kampanya_text;
        ImageView kampanya_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            kampanya_baslik=itemView.findViewById(R.id.kampanya_baslik);
            kampanya_text=itemView.findViewById(R.id.kampanya_text);
            kampanya_image=itemView.findViewById(R.id.kampanya_image);

        }
    }


}
