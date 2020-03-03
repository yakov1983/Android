package com.example.family;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button createBtn = findViewById(R.id.createBtn);
        Button joinBtn = findViewById(R.id.joinBtn);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterActivity();
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showJoinActivity();
            }
        });
    }

    public void showRegisterActivity() {
        Intent i = new Intent(this, RegistrActivity.class);

        startActivity(i);
    }

    public void showJoinActivity() {
        Intent i = new Intent(this, JoinActivity.class);

        startActivity(i);
    }
}
