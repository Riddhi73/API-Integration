package com.example.retrofit.NavFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.retrofit.ModelResponse.Fetchuserresponse;
import com.example.retrofit.ModelResponse.User;
import com.example.retrofit.R;
import com.example.retrofit.RetrofitClient;
import com.example.retrofit.UserAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserFragment extends Fragment {

    RecyclerView recyclerView;
    List<User> userList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<Fetchuserresponse> call = RetrofitClient.getInstance().getApi().fetchusers();

        call.enqueue(new Callback<Fetchuserresponse>() {
            @Override
            public void onResponse(Call<Fetchuserresponse> call, Response<Fetchuserresponse> response) {
                if (response.isSuccessful()){
                    userList = response.body().getUserList();
                    recyclerView.setAdapter(new UserAdapter(getActivity(),userList));
                }
                else{
                    Toast.makeText(getActivity(), response.body().getError(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Fetchuserresponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        super.onViewCreated(view, savedInstanceState);
    }
}