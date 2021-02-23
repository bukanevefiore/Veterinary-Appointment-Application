package com.example.mpets.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.mpets.Fragments.HomeFragment;
import com.example.mpets.R;
import com.example.mpets.Utils.ChangeFragments;
import com.example.mpets.Utils.GetSharedPreferences;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    GetSharedPreferences getSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragment();
        tanimlamlamalar();
        kontrol();
    }

    public void getFragment(){

        ChangeFragments changeFragments=new ChangeFragments(MainActivity.this);
        changeFragments.change(new HomeFragment());

    }

    public void tanimlamlamalar(){
        getSharedPreferences=new GetSharedPreferences(MainActivity.this);
        sharedPreferences=getSharedPreferences.getSession();
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