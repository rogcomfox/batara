package com.nusantarian.batara.service;

import com.nusantarian.batara.model.Language;
import com.nusantarian.batara.model.LoginResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("auth/register")
    Call<ResponseBody> createUser(
            @Field("fullName") String fullName,
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginResponse> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("home/language")
    Call<List<Language>> getAllLanguage(@Header("Authorization") String token);

}
