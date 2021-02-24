package com.example.mpets.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mpets.Models.LoginModel;
import com.example.mpets.R;
import com.example.mpets.RestApi.ManagerAll;
import com.example.mpets.Utils.GetSharedPreferences;
import com.example.mpets.Utils.Warnings;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    TextInputEditText usernameSigInEditText,paswordSignInEditText;
    Button signInButon;
    TextView signUpGecis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        tanimlamalar();
        action();
    }

    public void tanimlamalar(){

        usernameSigInEditText=findViewById(R.id.usernameSigInEditText);
        paswordSignInEditText=findViewById(R.id.paswordSignInEditText);
        signInButon=findViewById(R.id.signInButon);
        signUpGecis=findViewById(R.id.signUpGecis);
    }

    public void action(){

        signInButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=usernameSigInEditText.getText().toString();
                String pass=paswordSignInEditText.getText().toString();
                loginRequest(username,pass);
                delete();
            }
        });

        // kayıt ol activitye geçiş
        signUpGecis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void delete(){
        usernameSigInEditText.setText("");
        paswordSignInEditText.setText("");
    }

    public void loginRequest(String userName,String password){

        Call<LoginModel> request= ManagerAll.getInstance().girisYap(userName,password);
        request.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.body().isTf()){

                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignInActivity.this,MainActivity.class);
                    // kalıcı kaydı gerçekleştirmek için shared preferences sınıfını çağırıyoruz
                    GetSharedPreferences getSharedPreferences=new GetSharedPreferences(SignInActivity.this);
                    // set işlemleri
                    getSharedPreferences.setSession(response.body().getId(),response.body().getUserName(),response.body().getMailAdres());
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

                Toast.makeText(getApplicationContext(), Warnings.internetProblemText, Toast.LENGTH_LONG).show();
                Log.e("loginHata",t.getMessage());
            }
        });

    }
}