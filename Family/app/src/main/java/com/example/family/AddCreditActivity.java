package com.example.family;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.family.api.APIBuilder;
import com.example.family.model.AddFinanceRequest;
import com.example.family.model.AddFinanceResponce;
import com.example.family.model.CreditLabels;
import com.example.family.utils.Dialog;

import java.util.Calendar;

public class AddCreditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit);

        Spinner credits = findViewById(R.id.credits);

        final EditText creditLabel = findViewById(R.id.creditLabel);
        final EditText sum = findViewById(R.id.sum);
        final EditText date = findViewById(R.id.date);
        final EditText time = findViewById(R.id.time);

        final Button okBtn = findViewById(R.id.okBtn);

        final String []data = new String[CreditLabels.values().length];

        int i = 0;
        for (CreditLabels label : CreditLabels.values()) {
            data[i++] = label.get_label();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.support_simple_spinner_dropdown_item, data);
        credits.setAdapter(adapter);

        credits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CreditLabels label = CreditLabels.values()[position];

                if (label == CreditLabels.OTHER) {
                    creditLabel.setEnabled(true);
                    creditLabel.setText("");
                    creditLabel.requestFocus();
                } else {
                    creditLabel.setEnabled(false);
                    creditLabel.setText(label.get_label());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Calendar calendar = Calendar.getInstance();
                String dateStr = String.format("%d/%d/%d",
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.YEAR));
                date.setText(dateStr);

                String timeStr = String.format("%d/%d/%d",
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        calendar.get(Calendar.SECOND));
                time.setText(timeStr);

                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (creditLabel.getText().toString().equals("")) {
                            Dialog.showErrorDialog(AddCreditActivity.this, "Укажите статью затрат");
                            return;
                        }

                        if (sum.getText().toString().equals("")) {
                            Dialog.showErrorDialog(AddCreditActivity.this, "Укажите сумму затрат");
                            return;
                        }

                        if (date.getText().toString().equals("")) {
                            Dialog.showErrorDialog(AddCreditActivity.this, "Укажите дату");
                            return;
                        }

                        if (time.getText().toString().equals("")) {
                            Dialog.showErrorDialog(AddCreditActivity.this, "Укажите время");
                            return;
                        }

                        AddFinanceRequest reqest = new AddFinanceRequest();
                        reqest.creditLabel = creditLabel.getText().toString();
                        reqest.date = date.getText().toString() + " " + time.getText().toString();

                        try {
                            reqest.sum = Float.valueOf(sum.getText().toString());
                        } catch (Exception e) {
                            Dialog.showErrorDialog(AddCreditActivity.this, e.getMessage());
                            return;
                        }
                        addCredit(reqest);
                    }
                });
            }
        });
    }

    public void addCredit(AddFinanceRequest reqest) {
        APIBuilder<AddFinanceRequest, AddFinanceResponce> builder = new APIBuilder<>();

        builder.execute("addFinance", reqest, AddFinanceResponce.class, new APIBuilder.onCallback<AddFinanceResponce>() {
            @Override
            public void onResponse(AddFinanceResponce r) {
                if (!r.result) {
                    Dialog.showErrorDialog(AddCreditActivity.this, r.error);
                    return;
                }
                ///  программно нажимает кнопку "назад"

                onBackPressed();
            }

            @Override
            public void onError(Exception e) {

                Dialog.showErrorDialog(AddCreditActivity.this, e.getMessage());
            }
        });
    }
}
