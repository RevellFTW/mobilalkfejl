package com.example.mystandardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PackageModifyActivity extends AppCompatActivity {

    private PackageDatabaseHelper _packageDbHelper = new PackageDatabaseHelper(this);
    private RadioGroup dataGroup;
    private RadioGroup smsGroup;
    private long _packageId;
    private Package _mobilePackage;

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
        _mobilePackage = _packageDbHelper.getPackage(_packageId);
        dataGroup = findViewById(R.id.data_options);
        smsGroup = findViewById(R.id.sms_options);

        int dataOptionID = _mobilePackage.getDataPackage();
        int smsOptionID = _mobilePackage.getSmsPackage();
        //todo for some reason these buttons are null
        RadioButton radioButton = dataGroup.findViewById(dataOptionID);
        if (radioButton != null) {
            radioButton.setChecked(true);
        }
        RadioButton radioButton2 = smsGroup.findViewById(smsOptionID);
        if (radioButton2 != null) {
            radioButton2.setChecked(true);
        }
    }

    public void updatePackage(View view) {
        RadioButton selectedDataOption = findViewById(dataGroup.getCheckedRadioButtonId());
        RadioButton selectedSmsOption = findViewById(smsGroup.getCheckedRadioButtonId());

        int dataOptionID = selectedDataOption.getId();
        String dataOption = selectedDataOption.getText().toString();
        int smsOptionID = selectedSmsOption.getId();
        String smsOption = selectedSmsOption.getText().toString();

        Package packageEntry = new Package(dataOptionID, smsOptionID, _mobilePackage.getMobileNumber(), dataOption, smsOption);
        _packageDbHelper.updatePackage(packageEntry, _packageId);
    }

    public void deletePackage(View view) {
        _packageDbHelper.deletePackage(_packageId);
    }

    public void goBack(View view) {
        finish();
    }
}