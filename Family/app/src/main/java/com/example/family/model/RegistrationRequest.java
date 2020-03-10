package com.example.family.model;

import com.google.gson.annotations.SerializedName;

public class RegistrationRequest {
    @SerializedName("name")
    public String name;
    @SerializedName("password")
    public String password;
    @SerializedName("email")
    public String email;
}
