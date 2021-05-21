package com.example.retrofit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.ModelResponse.LoginResponse;
import com.example.retrofit.R;
import com.example.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email,password;
    TextView registerlink;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.logemail);
        password = findViewById(R.id.logpassword);
        registerlink = findViewById(R.id.createlink);
        login = findViewById(R.id.login);
        getSupportActionBar().hide();

        registerlink.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                userLogin();
                break;
            case R.id.createlink:
                startActivity(new Intent(loginActivity.this, MainActivity.class));
                break;
        }
    }

    private void userLogin() {


        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();


        if (useremail.isEmpty()){
            email.requestFocus();
            email.setError("Please enter your email");
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
            email.requestFocus();
            email.setError("Please enter correct email");
            return;
        }

        if (userpassword.isEmpty()){
            password.requestFocus();
            password.setError("Please enter your password");
            return;
        }

        if (userpassword.length()<8){
            password.requestFocus();
            password.setError("Minimum length 8");
            return;
        }

        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().login(useremail,userpassword);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();
                if (response.isSuccessful()){

                    Intent intent = new Intent(loginActivity.this,HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(loginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();



                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(loginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });




    }
}