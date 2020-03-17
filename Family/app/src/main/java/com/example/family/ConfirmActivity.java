package com.example.family;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.family.api.APIBuilder;
import com.example.family.api.APIService;
import com.example.family.model.ConfirmRequest;
import com.example.family.model.ConfirmResponce;
import com.example.family.model.LoginResponse;
import com.example.family.model.RegistrationRequest;
import com.example.family.model.RegistrationResponce;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        final EditText code = findViewById(R.id.code);
        Button confirmBtn = findViewById(R.id.confirmBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (code.getText().toString().equals("")) {
                    showError("Введите код!");
                    return;
                }

                confirmCode(code.getText().toString());
            }
        });
    }

    public void showError(String error) {
        ///  создаем всплывающий диалог с ошибкой
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(error);  // сюда передается ошибка
        alert.setTitle("Ошибка");  /// заголовок ошибки
        alert.setCancelable(true);   //
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {  // создаем кнопку
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ///   если пустой окно закрывается
            }
        });
        //alert.setIcon(R.drawable.ic_launcher_foreground)  - создание иконки

        alert.create().show();  //  метод вывода ошибки на экран
    }

    public void confirmCode(String code) {
        // запрос на сервер
        ConfirmRequest r = new ConfirmRequest();  /// модель в которой описаны поля для отправки запроса
        r.code = code;


        APIBuilder<ConfirmRequest, ConfirmResponce> builder = new APIBuilder<>();

        builder.execute("confirm", r, ConfirmResponce.class,
                new APIBuilder.onCallback<ConfirmResponce>() {

                    @Override
                    public void onResponse(ConfirmResponce resp) {
                        if (!resp.result) {
                            showError(resp.error);
                        } else {
                            showMenuActivity();
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        showError(e.getMessage());
                    }
                });

 /*      APIService
                .getInstance()
                .getAPI()
                .confirm(r)
                .enqueue(new Callback<ConfirmResponce>() {  ///   для общения с сервером(отправляет запрос на сервер)
                    @Override  // если все в порядке
                    public void onResponse(Call<ConfirmResponce> call, Response<ConfirmResponce> response) {
                        ConfirmResponce resp = null;

                        if(!response.isSuccessful()) {

                            Gson g = new Gson();
                            resp = g.fromJson(response.errorBody().charStream(), ConfirmResponce.class);
                        } else {
                            resp = response.body();
                        }

                        if (!resp.result) {
                            showError(resp.error);
                        } else {
                            // перход на подтверждение

                            showMenuActivity();
                        }
                    }

                    @Override  /// если ошибка
                    public void onFailure(Call<ConfirmResponce> call, Throwable t) {
                        showError(t.getMessage());
                    }
                });

  */
    }

    public void showMenuActivity() {
            Intent i = new Intent(this, MenuActivity.class);  ///
            startActivity(i);
        }
}
