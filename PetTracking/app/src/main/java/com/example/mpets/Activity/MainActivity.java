package com.example.mpets.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.example.mpets.Fragments.HomeFragment;
import com.example.mpets.R;
import com.example.mpets.Utils.ChangeFragments;
import com.example.mpets.Utils.GetSharedPreferences;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    GetSharedPreferences getSharedPreferences;
    MaterialButtonToggleGroup home_menu_buton;
    ChangeFragments changeFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragment();
        tanimlamlamalar();
        kontrol();
        action();
    }

    public void getFragment(){

        changeFragments=new ChangeFragments(MainActivity.this);
        changeFragments.change(new HomeFragment());


}

    public void action(){
        home_menu_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new HomeFragment());
            }
        });


    }

    public void tanimlamlamalar(){
        getSharedPreferences=new GetSharedPreferences(MainActivity.this);
        sharedPreferences=getSharedPreferences.getSession();
        home_menu_buton=findViewById(R.id.home_menu_buton);
    }

    public void kontrol(){

        if(sharedPreferences.getString("id",null) == null && sharedPreferences.getString("username",null) == null
        && sharedPreferences.getString("mailAdres",null) == null)
        {
            Intent intent=new Intent(MainActivity.this,SignInActivity.class);
            startActivity(intent);
            finish();

        }
        else{


        }
    }
}