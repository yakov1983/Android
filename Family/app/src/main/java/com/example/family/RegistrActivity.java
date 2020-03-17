package com.example.family;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.family.api.APIBuilder;
import com.example.family.api.APIService;
import com.example.family.model.LoginRequest;
import com.example.family.model.LoginResponse;
import com.example.family.model.RegistrationRequest;
import com.example.family.model.RegistrationResponce;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);

        final EditText email = findViewById(R.id.email);
        final EditText name = findViewById(R.id.name);
        final EditText password = findViewById(R.id.password);
        final EditText confirmPassword = findViewById(R.id.confirmPassword);

        Button okBtn = findViewById(R.id.okBtn);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String error = "";

                if (email.getText().toString().equals("")) {
                    error += "Укажите Email\n";
                }

                if (name.getText().toString().equals("")) {
                    error += "Укажите Имя\n";
                }

                if (password.getText().toString().equals("")) {
                    error += "Укажите Пароль\n";
                }

                if (confirmPassword.getText().toString().equals("")) {
                    error += "Подтвердите Пароль\n";
                }

                if (!password.getText().toString().equals("") &&
                    !confirmPassword.getText().toString().equals("")) {

                    if (!password.getText().toString().equals(confirmPassword.getText().toString())) {

                        error += "Пароли должны совпадать";
                    }
                }
                if (!error.equals("")) {
                    showError(error);
                    return;
                }

                registrUser(name.getText().toString(),    ////  если все в порядке
                            email.getText().toString(),
                            password.getText().toString());
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

    public void registrUser(String name, String email, String password) {
        RegistrationRequest r = new RegistrationRequest();  /// модель в которой описаны поля для отправки запроса
        r.email = email;
        r.password = password;
        r.name = name;

        APIBuilder<RegistrationRequest, RegistrationResponce> builder = new APIBuilder<>();

        builder.execute("registration", r, RegistrationResponce.class,
                new APIBuilder.onCallback<RegistrationResponce>() {

            @Override
            public void onResponse(RegistrationResponce resp) {
                if (!resp.result) {
                    showError(resp.error);
                } else {
                    showConfirmActivity();
                }
            }

            @Override
            public void onError(Exception e) {
                showError(e.getMessage());
            }
        });

/*        APIService
                .getInstance()
                .getAPI()
                .registration(r)
                .enqueue(new Callback<RegistrationResponce>() {  ///   для общения с сервером(отправляет запрос на сервер)
                    @Override  // если все в порядке
                    public void onResponse(Call<RegistrationResponce> call, Response<RegistrationResponce> response) {
                        RegistrationResponce resp = null;

                        if(!response.isSuccessful()) {

                            Gson g = new Gson();
                            resp = g.fromJson(response.errorBody().charStream(), RegistrationResponce.class);
                        } else {
                            resp = response.body();
                        }

                        if (!resp.result) {
                            showError(resp.error);
                        } else {
                            // перход на подтверждение

                            showConfirmActivity();
                        }
                    }

                    @Override  /// если ошибка
                    public void onFailure(Call<RegistrationResponce> call, Throwable t) {
                        showError(t.getMessage());
                    }
                });

 */
    }

    public void showConfirmActivity() {
        Intent i = new Intent(this, ConfirmActivity.class);  ///
        startActivity(i);
    }
}
