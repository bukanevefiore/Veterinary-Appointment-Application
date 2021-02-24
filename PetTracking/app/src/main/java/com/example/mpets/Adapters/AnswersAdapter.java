package com.example.mpets.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpets.Models.AnswersModel;
import com.example.mpets.Models.DeleteAnswerModel;
import com.example.mpets.Models.PetModel;
import com.example.mpets.R;
import com.example.mpets.RestApi.ManagerAll;
import com.example.mpets.Utils.Warnings;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder>{

    private Context context;
    private List<AnswersModel> list;

    public AnswersAdapter(List<AnswersModel> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.cevap_view_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.c_soruText.setText("Soru :" +list.get(position).getSoru().toString());
        holder.c_cevapText.setText("Cevap : "+list.get(position).getCevap().toString());

        holder.cevapsilButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAnswerRequest(list.get(position).getCevapid().toString(),list.get(position).getSoruid().toString(),position);
            }
        });
    }

    public void deleteAnswerRequest(String cevapid,String soruid,int pos){

        Call<DeleteAnswerModel> request= ManagerAll.getInstance().deleteAnswer(cevapid,soruid);
        request.enqueue(new Callback<DeleteAnswerModel>() {
            @Override
            public void onResponse(Call<DeleteAnswerModel> call, Response<DeleteAnswerModel> response) {

                if(response.body().isTf()) {
                    if(response.isSuccessful()) {
                        Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();
                        answerDeleteListGuncelle(pos);
                    }
                }
                else{
                    Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();

                }
            }



            @Override
            public void onFailure(Call<DeleteAnswerModel> call, Throwable t) {

                Toast.makeText(context, Warnings.internetProblemText, Toast.LENGTH_SHORT).show();
                Log.e("cevapsilkontrol",t.getMessage());

            }
        });

    }
    public void answerDeleteListGuncelle(int position){

        list.remove(position);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // view elemanlarını tanımlamak için bir iner class oluştuuryoruz
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView c_soruText,c_cevapText;
        ImageView cevapsilButon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            c_soruText=itemView.findViewById(R.id.c_soruText);
            c_cevapText=itemView.findViewById(R.id.c_cevapText);
            cevapsilButon=itemView.findViewById(R.id.cevapsilButon);

        }
    }


}
