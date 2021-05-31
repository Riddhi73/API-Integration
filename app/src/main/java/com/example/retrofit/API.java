package com.example.retrofit;

import com.example.retrofit.ModelResponse.Fetchuserresponse;
import com.example.retrofit.ModelResponse.LoginResponse;
import com.example.retrofit.ModelResponse.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(
           @Field("username") String username,
           @Field("email") String email,
           @Field("password") String password

    );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password

    );


    @GET("fetchusers.php")
    Call<Fetchuserresponse> fetchusers();

    @FormUrlEncoded
    @POST("updateuser.php")
    Call<LoginResponse> updateUserAccount(
            @Field("id") int userId,
            @Field("username") String userName,
            @Field("email") String email

    );

}
