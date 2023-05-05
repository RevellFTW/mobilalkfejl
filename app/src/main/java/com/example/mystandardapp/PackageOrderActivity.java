package com.example.mystandardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PackageOrderActivity extends AppCompatActivity {
    private PackageDatabaseHelper _packageDbHelper = new PackageDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_order);

    }

    public void buyPackage(View view) {
        RadioGroup dataGroup = findViewById(R.id.data_options);
        if(dataGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please select a data option", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton selectedDataOption = findViewById(dataGroup.getCheckedRadioButtonId());

        RadioGroup smsGroup = findViewById(R.id.sms_options);
        if(smsGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please select an sms option", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton selectedSmsOption = findViewById(smsGroup.getCheckedRadioButtonId());
        EditText phoneNumberEditText = findViewById(R.id.phoneEditText);
        String dataOptionText = selectedDataOption.getText().toString();
        int dataId = selectedDataOption.getId();
        String smsOptionText = selectedSmsOption.getText().toString();
        int smsId = selectedSmsOption.getId();
        String phoneNumber = phoneNumberEditText.getText().toString();

        if(phoneNumber.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean success = _packageDbHelper.insertData(dataOptionText, smsOptionText, phoneNumber, smsId, dataId);

        if(success) {
            String message = "You have purchased " + dataOptionText + " data and " + smsOptionText + ". We will contact your service provider to make the changes";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewPackages(View view) {
        Intent intent = new Intent(this, PackageListActivity.class);
        startActivity(intent);
    }
}