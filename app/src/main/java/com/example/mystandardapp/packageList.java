package com.example.mystandardapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class packageList extends AppCompatActivity {
    private MobileOrderViewModel mobileOrderViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);
        mobileOrderViewModel = new ViewModelProvider(this).get(MobileOrderViewModel.class);
        mobileOrderViewModel.getAllMobileOrders().observe(this, new Observer<List<MobileOrder>>() {
            @Override
            public void onChanged(List<MobileOrder> mobileOrders) {
                adapter.setMobileOrders(mobileOrders);
            }
        });
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