package com.example.mystandardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PackageModifyActivity extends AppCompatActivity {

    private Package packageEntry;
    private PackageDatabaseHelper _packageDbHelper = new PackageDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_modify);
        //get package from intent
        packageEntry = new Package(0,"1GB", "100");
    }

    public void updatePackage(View view) {
        //todo get id from intent
        RadioGroup dataGroup = findViewById(R.id.data_options);
        RadioButton selectedDataOption = findViewById(dataGroup.getCheckedRadioButtonId());

        RadioGroup smsGroup = findViewById(R.id.sms_options);
        RadioButton selectedSmsOption = findViewById(smsGroup.getCheckedRadioButtonId());

        String dataOptionText = selectedDataOption.getText().toString();
        String smsOptionText = selectedSmsOption.getText().toString();

        Package packageEntry = new Package(dataOptionText, smsOptionText);
        _packageDbHelper.updatePackage(packageEntry);
    }

    public void deletePackage(View view) {
        //todo get id from intent
        int id = this.packageEntry.getId();
        _packageDbHelper.deletePackage(id);
    }
}