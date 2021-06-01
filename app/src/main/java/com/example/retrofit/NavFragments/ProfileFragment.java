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
import com.example.retrofit.ModelResponse.UpdatePassResponse;
import com.example.retrofit.R;
import com.example.retrofit.RetrofitClient;
import com.example.retrofit.SharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment implements View.OnClickListener {


    EditText etuserName,etuserEmail,currentPass,newPass;
    Button updateuseracntbtn,updateuserpassbtn;
    SharedPreference sharedPreference;
    int userId;
    String userEmailId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        etuserName = view.findViewById(R.id.username_profile);
        etuserEmail = view.findViewById(R.id.useremail_profile);
        updateuseracntbtn = view.findViewById(R.id.btnupdateaccount);
        updateuserpassbtn = view.findViewById(R.id.btnupdatepassword);
        currentPass = view.findViewById(R.id.currentPass);
        newPass = view.findViewById(R.id.newPass);

        sharedPreference = new SharedPreference(getActivity());

        userId = sharedPreference.getuser().getId();

        userEmailId = sharedPreference.getuser().getEmail();

        updateuseracntbtn.setOnClickListener(this);
        updateuserpassbtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnupdateaccount:
                updateuser();
                break;
            case R.id.btnupdatepassword:
                updatepassword();
                break;

        }
    }

    private void updatepassword() {
        String usercurrentPass = currentPass.getText().toString().trim();
        String usernewPass = newPass.getText().toString().trim();


        if (usercurrentPass.isEmpty()){
            currentPass.setError("Enter Current Password");
            currentPass.requestFocus();
            return;
        }

        if(usercurrentPass.length() < 8){
            currentPass.setError("Enter 8 digit Password");
            currentPass.requestFocus();
            return;
        }

        if (usernewPass.isEmpty()){
            newPass.setError("Enter New Password");
            newPass.requestFocus();
            return;
        }

        if(usernewPass.length() < 8){
            newPass.setError("Enter 8 digit Password");
            newPass.requestFocus();
            return;
        }

        Call<UpdatePassResponse> call = RetrofitClient.
                getInstance().
                getApi().
                updateUserpass(userEmailId,usercurrentPass,usernewPass);

        call.enqueue(new Callback<UpdatePassResponse>() {
            @Override
            public void onResponse(Call<UpdatePassResponse> call, Response<UpdatePassResponse> response) {
                UpdatePassResponse updatePassResponse = response.body();

                if (response.isSuccessful()){
                    if (updatePassResponse.getError().equals("200")){
                        Toast.makeText(getActivity(), updatePassResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdatePassResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });







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
                        sharedPreference.SaveUser(updateResponse.getUser());
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