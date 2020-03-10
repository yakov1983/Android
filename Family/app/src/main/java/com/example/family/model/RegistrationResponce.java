package com.example.family.model;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponce {
    @SerializedName("result")
    public boolean result;
    @SerializedName("error")
    public String error;
}
