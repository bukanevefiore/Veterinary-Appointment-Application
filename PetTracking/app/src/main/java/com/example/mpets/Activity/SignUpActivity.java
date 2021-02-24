package com.example.mpets.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mpets.Models.RegisterModel;
import com.example.mpets.R;
import com.example.mpets.RestApi.ManagerAll;
import com.example.mpets.Utils.Warnings;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText mailEditText,usernameEditText,paswordEditText;
    TextView signInGecis;
    Button signUpButon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tanimlamalar();
        registerToUser();
    }

    public void tanimlamalar(){

        mailEditText=findViewById(R.id.mailEditText);
        usernameEditText=findViewById(R.id.usernameEditText);
        paswordEditText=findViewById(R.id.paswordEditText);
        signInGecis=findViewById(R.id.signInGecis);
        signUpButon=findViewById(R.id.signUpButon);
    }

    public void registerToUser(){

        signUpButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail=mailEditText.getText().toString();
                String userN=usernameEditText.getText().toString();
                String pass=paswordEditText.getText().toString();
                registerRequest(mail,userN,pass);
                delete();

            }
        });

        signInGecis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void delete(){
        mailEditText.setText("");
        usernameEditText.setText("");
        paswordEditText.setText("");

    }

    public void registerRequest(String userMailAdres,String userName,String userPass){

        Call<RegisterModel> req= ManagerAll.getInstance().kayitOl(userMailAdres,userName,userPass);
        req.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {

                if(response.body().isTf()){

                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignUpActivity.this,SignInActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {

                Toast.makeText(getApplicationContext(), Warnings.internetProblemText, Toast.LENGTH_LONG).show();
                Log.e("registerHata",t.getMessage());

            }
        });
    }
}