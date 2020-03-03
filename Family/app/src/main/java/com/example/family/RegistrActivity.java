package com.example.family;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                }
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
}
