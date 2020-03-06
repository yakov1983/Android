package com.example.family.api;

import com.example.family.model.LoginRequest;
import com.example.family.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MyFamily {
    ///   прописываем конечные точки
    @POST("/login")
    Call<LoginResponse> login (@Body LoginRequest r);
}
