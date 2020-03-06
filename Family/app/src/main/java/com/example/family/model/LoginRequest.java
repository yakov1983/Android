package com.example.family.model;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    //// для отправки данных на сервер

    @SerializedName("email")
    public  String email;
    @SerializedName("password")
    public String password;
}
