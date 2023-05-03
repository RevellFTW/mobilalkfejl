package com.example.mystandardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PackageModifyActivity extends AppCompatActivity {

    private PackageDatabaseHelper _packageDbHelper = new PackageDatabaseHelper(this);
    private long _packageId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_modify);
        //get package from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            _packageId = extras.getInt("packageId");
            //The key argument here must match that used in the other activity
        }
    }

    public void updatePackage(View view) {
        //todo preselect the radio buttons
        RadioGroup dataGroup = findViewById(R.id.data_options);
        RadioButton selectedDataOption = findViewById(dataGroup.getCheckedRadioButtonId());

        RadioGroup smsGroup = findViewById(R.id.sms_options);
        RadioButton selectedSmsOption = findViewById(smsGroup.getCheckedRadioButtonId());

        String dataOptionText = selectedDataOption.getText().toString();
        String smsOptionText = selectedSmsOption.getText().toString();

        Package packageEntry = new Package(dataOptionText, smsOptionText);
        _packageDbHelper.updatePackage(packageEntry, _packageId);
    }

    public void deletePackage(View view) {
        _packageDbHelper.deletePackage(_packageId);
    }
}