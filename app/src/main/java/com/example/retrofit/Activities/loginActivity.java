package com.example.retrofit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.retrofit.R;

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
                startActivity(new Intent(loginActivity.this, HomeActivity.class));
                break;
            case R.id.createlink:
                startActivity(new Intent(loginActivity.this, MainActivity.class));
                break;
        }
    }
}