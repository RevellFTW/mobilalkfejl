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
        dataGroup = findViewById(R.id.data_options);
        smsGroup = findViewById(R.id.sms_options);

        Package pack = _packageDbHelper.getPackage(_packageId);
        int dataOptionID = pack.getDataPackage();
        int smsOptionID = pack.getSmsPackage();

        RadioButton radioButton = dataGroup.findViewById(dataOptionID);
        radioButton.setChecked(true);
        RadioButton radioButton2 = smsGroup.findViewById(smsOptionID);
        radioButton2.setChecked(true);
    }

    public void updatePackage(View view) {
        //todo preselect the radio buttons
        RadioButton selectedDataOption = findViewById(dataGroup.getCheckedRadioButtonId());
        RadioButton selectedSmsOption = findViewById(smsGroup.getCheckedRadioButtonId());

        int dataOptionID = selectedDataOption.getId();
        int smsOptionID = selectedSmsOption.getId();

        Package packageEntry = new Package(dataOptionID, smsOptionID);
        _packageDbHelper.updatePackage(packageEntry, _packageId);
    }

    public void deletePackage(View view) {
        _packageDbHelper.deletePackage(_packageId);
    }

    public void goBack(View view) {
        finish();
    }
}