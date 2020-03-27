package com.example.family.api;

import com.example.family.model.AddFinanceRequest;
import com.example.family.model.AddFinanceResponce;
import com.example.family.model.ConfirmRequest;
import com.example.family.model.ConfirmResponce;
import com.example.family.model.FinanceResponse;
import com.example.family.model.JoinRequest;
import com.example.family.model.JoinResponce;
import com.example.family.model.LoginRequest;
import com.example.family.model.LoginResponse;
import com.example.family.model.RegistrationRequest;
import com.example.family.model.RegistrationResponce;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface MyFamily {
    ///   прописываем конечные точки
    @POST("/login")
    Call<LoginResponse> login (@Body LoginRequest r);

    ///
    @POST("/register")
    Call<RegistrationResponce> registration (@Body RegistrationRequest r);

    ///
    @POST("/submit")
    Call<ConfirmResponce> confirm (@Body ConfirmRequest r);

    ///
    @POST("/join")
    Call<JoinResponce> join (@Body JoinRequest r);

    ////
    @GET("/finance")
    Call<FinanceResponse> getFinance(@Query("startAt") String start,
                                     @Query("endAt") String end);
    ////  дополнительные параметры в запросе GET передаются в Query
    ///  в Query  будет читаться та часть адреса после знака вопроса?
    ////  параметры задаются в виде key=value
    ////  между собой параметры разделяютс знаком &
    //<server>/finance? startAt=01-01-2020&endAt=31-01-2020

    ////чтобы задать Query-параметр в Retrofit, необходимо использовать директиву
    ///  @Query("key")
    ///  Retrofit автоматически задаст параметру значение , указанное в аргументе, следующем за директивой

    ////

    @PUT("/finance")
    Call<AddFinanceResponce> addFinance (@Body AddFinanceRequest r);
}
