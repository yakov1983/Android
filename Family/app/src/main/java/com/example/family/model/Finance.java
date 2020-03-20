package com.example.family.model;

import com.google.gson.annotations.SerializedName;

public class Finance {
    @SerializedName("sum")
    public float sum;
    @SerializedName("name")
    public String name;
    @SerializedName("creditLabel")
    public  String creditLabel;
    @SerializedName("date")
    public  String date;
}
