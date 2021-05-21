package com.example.retrofit.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit.ModelResponse.RegisterResponse;
import com.example.retrofit.R;
import com.example.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView login;
    EditText name,email,password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = findViewById(R.id.etname);
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpassword);

        register = findViewById(R.id.register);

        login = findViewById(R.id.loginlink);
        register.setOnClickListener(this);

        login.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                registerUser();
                break;
            case R.id.loginlink:
                startActivity(new Intent(MainActivity.this, loginActivity.class));
                break;
        }
    }

    private void registerUser() {
        String username = name.getText().toString();
        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();

        if (username.isEmpty()){
            name.requestFocus();
            name.setError("Please enter your name");
            return;
        }

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

        Call<RegisterResponse> call = RetrofitClient.getInstance().getApi().register(username,useremail,userpassword);
        
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}