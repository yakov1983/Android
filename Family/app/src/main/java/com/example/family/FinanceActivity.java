package com.example.family;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.family.adapter.FinanceAdapter;
import com.example.family.api.APIBuilder;
import com.example.family.model.Finance;
import com.example.family.model.FinanceResponse;
import com.example.family.utils.Dialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class FinanceActivity extends AppCompatActivity {
    /////TODO: remove
    private Finance[] fakeFinances = {
            new Finance(12.5f, "Vasya", "shopping", "24/03/2012 18:44:45"),
            new Finance(9.5f, "Petya", "market", "25/06/2013 20:14:45"),
            new Finance(23.5f, "KolyaKolyaKolyaKolya", "butchers", "26/02/2014 22:24:45"),
            new Finance(23.5f, "Kolya", "butchers", "26/02/2020 22:24:45")
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
                Dialog.showCalendarDialog(FinanceActivity.this, new Dialog.onDateChangeListener() {
                    @Override
                    public void onDateChange(int year, int month, int day) {
                        fetchFinances(year, month, day);
                    }
                });
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

        ///  TODO: uncomment
        Calendar calendar = Calendar.getInstance();
        /*fetchFinances(calendar.get(Calendar.YEAR),
                      calendar.get(Calendar.MONTH),
                      calendar.get(Calendar.DAY_OF_MONTH));*/
    }

    public void fetchFinances(int year, int month, int day) {
        APIBuilder<String, FinanceResponse> builder = new APIBuilder<>();
        builder.execute("getFinance",
                             String.format("%d/%d/%d", year, month, day),
                             FinanceResponse.class, new APIBuilder.onCallback<FinanceResponse>() {
                             @Override
                             public void onResponse(FinanceResponse r) {
                                if(r.result) {
                                    Dialog.showErrorDialog(FinanceActivity.this, r.error);
                                    return;
                                }
                                showList(r.finances);
                             }

                             @Override
                             public void onError(Exception e) {

                                 Dialog.showErrorDialog(FinanceActivity.this, "Ничего не найдено за указанную дату");
                             }
        });
    }

    public void showList(Finance[] finances) {
        FinanceAdapter adapter = new FinanceAdapter(FinanceActivity.this, finances);
        this.financesList.setAdapter(adapter);
        this.financesList.notifyAll(); ///  насильно перерисует область с ListView на экране
    }
}
