package com.example.family.api;

import com.example.family.model.ConfirmRequest;
import com.example.family.model.ConfirmResponce;
import com.example.family.model.LoginRequest;
import com.example.family.model.LoginResponse;
import com.example.family.model.RegistrationRequest;
import com.example.family.model.RegistrationResponce;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MyFamily {
    ///   прописываем конечные точки
    @POST("/login")
    Call<LoginResponse> login (@Body LoginRequest r);

    ///
    @POST("/registration")
    Call<RegistrationResponce> registration (@Body RegistrationRequest r);

    ///
    @POST("/confirm")
    Call<ConfirmResponce> confirm (@Body ConfirmRequest r);
}
