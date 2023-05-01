package com.example.mystandardapp;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MobileOrderViewModel extends ViewModel {
    private MobileOrderRepository repository;
    private LiveData<List<MobileOrder>> mobileOrders;

    public MobileOrderViewModel(Application application){
        repository = new MobileOrderRepository(application);
        mobileOrders = repository.getAllMobileOrders();
    }

    public LiveData<List<MobileOrder>> getAllMobileOrders(){
        return mobileOrders;
    }

    public void insert(MobileOrder mobileOrder){
        repository.insert(mobileOrder);
    }

}
