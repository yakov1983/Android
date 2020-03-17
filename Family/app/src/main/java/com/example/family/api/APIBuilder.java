package com.example.family.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIBuilder<Req, Res> {

    public  interface onCallback<Res> {
        void onResponse (Res r);
        void onError(Exception e);
    }

    public void execute(String call, Req req, final Class <Res> respT, final onCallback callback) {
       MyFamily api = APIService.getInstance().getAPI();

       //  рефлексия

        ///  получаем метод интерфейса MyFamily по его названию

        try {

            Method method = api.getClass().getMethod(call, req.getClass());

            //   вызываем полученный метод

            Call<Res> request = (Call<Res>) method.invoke(api, req);  /// Down casting
            request.enqueue(new Callback<Res>() {
                @Override
                public void onResponse(Call<Res> call, Response<Res> response) {

                    Res res;

                    if (response.isSuccessful()) {
                        Gson g = new Gson();

                        res = g.fromJson(response.errorBody().charStream(), respT);
                    } else {
                        res = response.body();
                    }
                    callback.onResponse(res);
                }

                @Override
                public void onFailure(Call<Res> call, Throwable t) {

                    callback.onError(new Exception(t));
                }
            });
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}
