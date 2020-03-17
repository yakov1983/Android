package com.example.family;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.family.api.APIBuilder;
import com.example.family.api.APIService;
import com.example.family.model.LoginRequest;
import com.example.family.model.LoginResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  проверяем, выполнен ли вход

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        if (preferences.contains("API_TOKEN")) {
            showMenuActivity();
            return;
        }


        TextView sign_upBtn = findViewById(R.id.sign_upBtn);
        sign_upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStartActivity();
            }
        });

        final EditText login = (EditText) findViewById(R.id.login);
        final EditText password = (EditText) findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.loginBtn);

        errorMsg = findViewById(R.id.errorMsg);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String error = "";
                if(login.getText().toString().equals("")) {
                    error = "Заполните поле \"Логин\"\n";
                }

                if(password.getText().toString().equals("")) {
                    error += "Заполните поле \"Пароль\"\n";
                }

                if (!error.equals("")) {
                    errorMsg.setText(error);
                    errorMsg.setVisibility(View.VISIBLE);
                    return;
                }

                //showMenuActivity();
                loginUser(login.getText().toString(), password.getText().toString());  ///
            }
        });

        TextWatcher t = new TextWatcher() {   //   добавляем событие изменения текста
            //
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                errorMsg.setText("");   //  убираем ошибку
                errorMsg.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        login.addTextChangedListener(t);
        password.addTextChangedListener(t);
    }
    public void showMenuActivity() {
        Intent i = new Intent(this, MenuActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    /// убирает все предыдущие активити
        startActivity(i);
    }

    public void showStartActivity() {
        Intent i = new Intent(this, StartActivity.class);  ///
        startActivity(i);
    }

    public void showError (String error) {
        errorMsg.setText(error);
        errorMsg.setVisibility(View.VISIBLE);
    }

    public void loginUser(String email, String password) {
        LoginRequest r = new LoginRequest();  /// модель в которой описаны поля для отправки запроса
        r.email = email;
        r.password = password;

        APIBuilder<LoginRequest, LoginResponse> builder = new APIBuilder<>();

        builder.execute("login", r, LoginResponse.class, new APIBuilder.onCallback<LoginResponse>() {
            @Override
            public void onResponse(LoginResponse resp) {
                if (!resp.result) {
                    showError(resp.error);
                } else {
                    // сохранить токен в памяти устройства
                    //будем сохранять токен в кэш приложения

                    SharedPreferences preferences =  ///  отвечает за работу кэша
                            PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("API_TOKEN", resp.token);
                    editor.apply();  ///   сохраняет на физическую память

                    ////  чтобы получить какое либо значение из кэш  вызываем preferences.getString
                    //preferences.getString("API_TOKEN", "default"); ключ и значение по умолчанию

                    showMenuActivity();
                }
            }

            @Override
            public void onError(Exception e) {

                showError(e.getMessage());
            }
        });

        ///////////     выше переделанный код         //////
/*        APIService
                .getInstance()
                .getAPI()
                .login(r)
                .enqueue(new Callback<LoginResponse>() {  ///   для общения с сервером(отправляет запрос на сервер)
                    @Override  // если все в порядке
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        LoginResponse resp = null;

                        if(!response.isSuccessful()) {

                            Gson g = new Gson();
                            resp = g.fromJson(response.errorBody().charStream(), LoginResponse.class);
                        } else {
                            resp = response.body();
                        }

                        if (!resp.result) {
                            errorMsg.setVisibility(View.VISIBLE);
                            errorMsg.setText(resp.error);
                        } else {
                            // сохранить токен в памяти устройства
                            //будем сохранять токен в кэш приложения

                            SharedPreferences preferences =
                                    PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("API_TOKEN", resp.token);
                            editor.apply();  ///   сохраняет на физическую память

                            ////  чтобы получить какое либо значение из кэш  вызываем preferences.getString
                            //preferences.getString("API_TOKEN", "default"); ключ и значение по умолчанию

                            showMenuActivity();
                        }
                    }

                    @Override  /// если ошибка
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        errorMsg.setVisibility(View.VISIBLE);
                        errorMsg.setText(t.getMessage());  ///  данный метод выведет ошибку
                    }
                });

 */
    }
}
