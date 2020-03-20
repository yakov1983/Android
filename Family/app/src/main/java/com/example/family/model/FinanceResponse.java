package com.example.family.model;

import com.google.gson.annotations.SerializedName;

public class FinanceResponse {

    @SerializedName("error")
    public String error;
    @SerializedName("result")
    public boolean result;
    @SerializedName("finances")
    public Finance[] finances;

}
