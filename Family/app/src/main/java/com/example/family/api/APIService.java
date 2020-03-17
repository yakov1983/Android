package com.example.family.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    // Создаем статический метод
    //  Паттерн Singleton

    private Retrofit mRetrofit;
    private  static  APIService instance;
    public static APIService getInstance() {
        if (instance == null) {
            //  инициализация instance
            instance = new APIService();

            // Паттерн FabricBuilder
            instance.mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://212f349f.ngrok.io")  // указываем адрес сервера
                    .addConverterFactory(GsonConverterFactory.create()) // для конвертации jsona
                    .build();
        }
        return instance;  ////  этим добьемся что этот метод в единственном экземпляре

    }
    public MyFamily getAPI() {
        return mRetrofit.create(MyFamily.class);  ///
    }
}
