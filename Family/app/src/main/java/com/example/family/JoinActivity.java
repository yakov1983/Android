package com.example.family;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.family.api.APIBuilder;
import com.example.family.model.JoinRequest;
import com.example.family.model.JoinResponce;
import com.example.family.utils.Dialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {

    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        email = findViewById(R.id.email);
        Button joinBtn = findViewById(R.id.joinBtn);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// Проверка пустое или нет поле
                if (email.getText().toString().equals("")) {
                    Dialog.showErrorDialog(JoinActivity.this, "укажите email");
                    return;
                }

                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(email.getText().toString());

                if (!matcher.matches()) {
                    Dialog.showErrorDialog(JoinActivity.this,"не верно указанный email");
                    return;
                }

                join(email.getText().toString());
            }
        });
    }

    public void join (String email) {
        JoinRequest r = new JoinRequest();
        r.email = email;
        APIBuilder<JoinRequest, JoinResponce> builder = new APIBuilder<>();
        builder.execute("join", r, JoinResponce.class, new APIBuilder.onCallback<JoinResponce>() {
            @Override
            public void onResponse(JoinResponce resp) {
                Dialog.showDialog(JoinActivity.this, "Пользователь добавлен!");

                //  очищаем поле для ввода Email
                JoinActivity.this.email.setText("");
            }

            @Override
            public void onError(Exception e) {

                Dialog.showErrorDialog(JoinActivity.this, e.getMessage());
            }
        });
    }
}
