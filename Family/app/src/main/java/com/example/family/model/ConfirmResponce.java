package com.example.family.model;

import com.google.gson.annotations.SerializedName;

public class ConfirmResponce {
    @SerializedName("token")
    public String token;
    @SerializedName("error")
    public String error;
    @SerializedName("result")
    public boolean result;
}
