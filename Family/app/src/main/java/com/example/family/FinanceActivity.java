package com.example.family;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.family.adapter.FinanceAdapter;
import com.example.family.model.Finance;
import com.example.family.utils.Dialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FinanceActivity extends AppCompatActivity {
    /////TODO: remove
    private Finance[] fakeFinances = {
            new Finance(12.5f, "Vasya", "shopping", "24/03/2012 18:44:45"),
            new Finance(9.5f, "Petya", "market", "25/06/2013 20:14:45"),
            new Finance(23.5f, "KolyaKolyaKolyaKolya", "butchers", "26/02/2014 22:24:45")
    };
    /////
    private ListView financesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        ImageButton calendarBtn = findViewById(R.id.calendarBtn);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog.showCalendarDialog(FinanceActivity.this);
            }
        });

        financesList = findViewById(R.id.financesList);

        FinanceAdapter adapter = new FinanceAdapter(this, fakeFinances);
        financesList.setAdapter(adapter);

        FloatingActionButton addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FinanceActivity.this, AddCreditActivity.class);
                startActivity(i);
            }
        });
    }
}
