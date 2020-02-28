package com.example.family;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText login = (EditText) findViewById(R.id.login);
        final EditText password = (EditText) findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.loginBtn);

        final TextView errorMsg = findViewById(R.id.errorMsg);
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

                showMenuActivity();
            }
        });
    }
    public void showMenuActivity() {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
}
