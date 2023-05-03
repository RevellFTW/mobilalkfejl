package com.example.mystandardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        RadioButton selectedDataOption = findViewById(dataGroup.getCheckedRadioButtonId());

        RadioGroup smsGroup = findViewById(R.id.sms_options);
        RadioButton selectedSmsOption = findViewById(smsGroup.getCheckedRadioButtonId());

        String dataOptionText = selectedDataOption.getText().toString();
        int dataOptionID = selectedDataOption.getId();
        String smsOptionText = selectedSmsOption.getText().toString();
        int smsOptionID = selectedSmsOption.getId();

        boolean success = _packageDbHelper.insertData(dataOptionID, smsOptionID);

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