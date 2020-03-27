package com.example.family.model;

import com.google.gson.annotations.SerializedName;

public class AddFinanceResponce {
    @SerializedName("result")
    public boolean result;
    @SerializedName("error")
    public String error;
}
