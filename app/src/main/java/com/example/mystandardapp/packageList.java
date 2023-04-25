package com.example.mystandardapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class packageList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);

    }

    public void buyPackage(View view) {
        RadioGroup dataGroup = findViewById(R.id.data_options);
        RadioButton selectedDataOption = findViewById(dataGroup.getCheckedRadioButtonId());

        RadioGroup smsGroup = findViewById(R.id.sms_options);
        RadioButton selectedSmsOption = findViewById(smsGroup.getCheckedRadioButtonId());

        String dataOptionText = selectedDataOption.getText().toString();
        String smsOptionText = selectedSmsOption.getText().toString();


        String message = "You have purchased " + dataOptionText + " data and " + smsOptionText + ". We will contact your service provider to make the changes";
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}