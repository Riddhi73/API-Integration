package com.example.retrofit.NavFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofit.ModelResponse.LoginResponse;
import com.example.retrofit.R;
import com.example.retrofit.RetrofitClient;
import com.example.retrofit.SharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment implements View.OnClickListener {


    EditText etuserName,etuserEmail;
    Button updateuseracntbtn;
    SharedPreference sharedPreference;
    int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        etuserName = view.findViewById(R.id.username_profile);
        etuserEmail = view.findViewById(R.id.useremail_profile);
        updateuseracntbtn = view.findViewById(R.id.btnupdateaccount);
        sharedPreference = new SharedPreference(getActivity());

        userId = sharedPreference.getuser().getId();

        updateuseracntbtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnupdateaccount:
                updateuser();
                break;

        }
    }

    private void updateuser() {
        String username = etuserName.getText().toString().trim();
        String email = etuserEmail.getText().toString().trim();
        if (username.isEmpty()){
            etuserName.setError("Please Enter Username");
            etuserName.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etuserEmail.requestFocus();
            etuserEmail.setError("Please enter correct email");
            return;
        }
        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().updateUserAccount(userId,username,email);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse updateResponse = response.body();
                if (response.isSuccessful()){
                    if (updateResponse.getError().equals("200")){

                        Toast.makeText(getActivity(), updateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getActivity(), updateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}