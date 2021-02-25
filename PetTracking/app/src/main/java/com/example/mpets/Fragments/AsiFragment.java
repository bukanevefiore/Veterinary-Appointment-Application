package com.example.mpets.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mpets.Models.AsiModel;
import com.example.mpets.R;
import com.example.mpets.RestApi.ManagerAll;
import com.example.mpets.Utils.GetSharedPreferences;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AsiFragment extends Fragment {
    private View view;
    private Context context;
    private CalendarPickerView calendarPickerView;
    private DateFormat format;
    private Calendar nextYear;  // takvimde ne kadar süreyi gösterecek
    private Date today;
    private List<AsiModel> asiList;
    private List<Date> dateList;  // pickerview in işeretlenmiş halini bize getirir
    private String id;
    GetSharedPreferences getSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_asi, container, false);

        tanimlamalar();
        asiTakipRequest();
        onClickCalendar();

        return view;
    }

    public void tanimlamalar(){

        calendarPickerView=view.findViewById(R.id.calendarPickerView);
        format=new SimpleDateFormat("dd/MM/yyyy");  // tarih formatını belirleme
        // 1 yıllık takvim görünmesi için
        nextYear=Calendar.getInstance();
        nextYear.add(Calendar.YEAR,1);   // 1 yıl
        today=new Date();

        calendarPickerView.init(today,nextYear.getTime());  // takvimde gösterilecek gün aralığını bugün ve 1 yıl sonraki olarak belirtiyoruz

        asiList=new ArrayList<>();
        dateList=new ArrayList<>();

        getSharedPreferences=new GetSharedPreferences(getActivity());
        id=getSharedPreferences.getSession().getString("id",null);


    }

    // aşı bilgilerini getirmme işlemleri
    public void asiTakipRequest(){

        Call<List<AsiModel>> request= ManagerAll.getInstance().getAsi(id);
        request.enqueue(new Callback<List<AsiModel>>() {
            @Override
            public void onResponse(Call<List<AsiModel>> call, Response<List<AsiModel>> response) {

                if(response.isSuccessful()) {
                    if (response.body().get(0).isTf()) {
                        asiList = response.body();
                        for (int i = 0; i < asiList.size(); i++) {

                            String dataString = response.body().get(i).getAsitarih().toString();
                            try {

                                Date date = format.parse(dataString);    // servisten aldığımız tarihi gerekli formata çevirme
                                dateList.add(date);  // veri tabanından aldığımız tarihleri datelist e atıyoruz


                            } catch (ParseException e) {
                                e.printStackTrace();
                                Log.e("tarihlist", e.getMessage());
                            }

                        }

                        try {
                            // takvimize listemizi de veriyoruz
                            calendarPickerView.init(today, nextYear.getTime())
                                    .withHighlightedDates(dateList);
                        } catch (Exception e) {

                            Log.e("inithata", e.getMessage());

                        }

                    }
                }
                else
                {
                    Toast.makeText(getContext(), "There is no vaccine for your pet at the future date!!", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<AsiModel>> call, Throwable t) {

                Log.e("tarihlististek",t.getMessage());

            }
        });

    }

    // takvimde tıklanan güne aitaşı var ise  ayrıntıların ekraana gelmesi
    public void onClickCalendar(){

        calendarPickerView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {

                for(int i=0;i<dateList.size();i++)
                {
                    // takvimde tıklananan güne göre aşı günlerinin bulunduğu datelist imizin kontrol edilmesi
                    if(date.toString().equals(dateList.get(i).toString()))
                    {

                        openAsiInfoAlert(asiList.get(i).getPetisim().toString(),asiList.get(i).getAsitarih().toString()
                        ,asiList.get(i).getAsiisim().toString(),asiList.get(i).getPetresim().toString());

                        Toast.makeText(getContext(), asiList.get(i).getPetisim(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

    }

    // takvimde aşı olan günlerden birine tıklanınca açılacak alert diyolog
    public void openAsiInfoAlert(String petIsmi,String tarih, String asiIsmi,String resimUrl){

        LayoutInflater layoutInflater=this.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.asi_takip_alert_layout,null);

        final TextView petisimText_asi,petBilgiText_asi;
        CircleImageView petImageView_asi;

        petisimText_asi=view.findViewById(R.id.petisimText_asi);
        petBilgiText_asi=view.findViewById(R.id.petBilgiText_asi);
        petImageView_asi=view.findViewById(R.id.petImageView_asi);

        petisimText_asi.setText(petIsmi);
        petBilgiText_asi.setText(petIsmi+" isimli petinizin "+tarih+" tarihinde "+asiIsmi+" aşısı vardır");
        Picasso.get().load("http://192.168.1.4/veterinary/"+resimUrl).into(petImageView_asi);


        // alert diyolog tetikleme
        AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
        alert.setView(view);
        // ekranın herhangi bir yerine tıklayınca alert diyoloğun kaybolması için:
        alert.setCancelable(true);
        AlertDialog alertDialog=alert.create(); // alert diyoloğu nesne üreterek oluşturma



        alertDialog.show();
    }
}