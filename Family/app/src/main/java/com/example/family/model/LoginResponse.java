package com.example.family.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("result")  // false если такого пользователя нет или произошла ошибка
    public boolean result;
    @SerializedName("error")  // записано какая ошибка
    public String error;
    @SerializedName("token")  /// строка о пользователе , по ней сервер проверит имеете ли вы доступ к приложению
    public String token;
}
