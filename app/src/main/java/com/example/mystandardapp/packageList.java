package com.example.mystandardapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

public class packageList extends AppCompatActivity {
    private static final String LOG_TAG = RegisterActivity.class.getName();
    private BillingClient billingClient;
    private SkuDetails data1GB;
    private SkuDetails data3GB;
    private SkuDetails data5GB;
    private SkuDetails sms50;
    private SkuDetails sms100;
    private SkuDetails sms200;
    private PurchasesUpdatedListener updatedListener = (billingResult, list) -> {
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {
            for (Purchase purchase : list) {
                Log.d(LOG_TAG,"MAIN" + "sikeres vasarlas");
            }
        }  else {
            Log.d(LOG_TAG, "MAIN" + "sikertelen vasarlas");

        }
    };
    private List<SkuDetails> skuDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);
        this.billingClient = BillingClient.newBuilder(this)
                .enablePendingPurchases()
                .setListener(updatedListener)
                .build();
        startConnection();
    }

    public void buyPackage(View view) {
        int dataPackage = 0;
        int smsCount = 0;
        switch (view.getId()) {
            case R.id.option_1gb:
                dataPackage = 1;
                break;
            case R.id.option_2gb:
                dataPackage = 3;
                break;
            case R.id.option_5gb:
                dataPackage = 5;
                break;
            case R.id.option_100_sms:
                smsCount = 50;
                break;
            case R.id.option_500_sms:
                smsCount = 100;
                break;
            case R.id.option_unlimited_sms:
                smsCount = 200000000;
                break;
        }
        if (dataPackage > 0 && smsCount > 0) {
            // Both data package and SMS count are selected
            SkuDetails dataSkuDetails = null;
            if (dataPackage == 1) {
                dataSkuDetails = data1GB;
            } else if (dataPackage == 3) {
                dataSkuDetails = data3GB;
            } else if (dataPackage == 5) {
                dataSkuDetails = data5GB;
            }
            SkuDetails smsSkuDetails = null;
            if (smsCount == 50) {
                smsSkuDetails = sms50;
            } else if (smsCount == 100) {
                smsSkuDetails = sms100;
            } else if (smsCount == 200) {
                smsSkuDetails = sms200;
            }

        }
    }
            private void startConnection() {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    List<String> dataPackages = new ArrayList<>();
                    dataPackages.add("1gb_data");
                    dataPackages.add("3gb_data");
                    dataPackages.add("5gb_data");
                    List<String> smsOptions = new ArrayList<>();
                    smsOptions.add("50_sms");
                    smsOptions.add("100_sms");
                    smsOptions.add("200_sms");
                    SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                    params.setSkusList(dataPackages).setType(BillingClient.SkuType.INAPP);
                    billingClient.querySkuDetailsAsync(params.build(), new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<SkuDetails> list) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                if (list != null) {
                                    for (SkuDetails skuDetails : list) {
                                        Log.d(LOG_TAG, "onSkuDetailsResponse: " + skuDetails.getTitle());
                                        Log.d(LOG_TAG, "onSkuDetailsResponse: " + skuDetails.getPrice());
                                        Log.d(LOG_TAG, "onSkuDetailsResponse: " + skuDetails.getDescription());
                                        if (skuDetails.getSku().equals("1GbPackage")) {
                                            data1GB = skuDetails;
                                        } else if (skuDetails.getSku().equals("3GbPackage")) {
                                            data3GB = skuDetails;
                                        } else if (skuDetails.getSku().equals("5GbPackage")) {
                                            data5GB = skuDetails;
                                        } else if (skuDetails.getSku().equals("sms50")) {
                                            sms50 = skuDetails;
                                        } else if (skuDetails.getSku().equals("sms100")) {
                                            sms100 = skuDetails;
                                        } else if (skuDetails.getSku().equals("sms200")) {
                                            sms200 = skuDetails;
                                        }
                                    }
                                    UpdateUI(list);
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                //todo
                startConnection();
            }
        });
    }

    private void UpdateUI(List<SkuDetails> list){
        this.skuDetails = list;
    }
}