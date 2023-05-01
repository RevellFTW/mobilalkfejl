package com.example.mystandardapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MobileOrderRepository {
    private MobileOrderDAO dao;
    private LiveData<List<MobileOrder>> mobileOrders;

    MobileOrderRepository(Application application){
        MobileOrderRoomDatabase db = MobileOrderRoomDatabase.getInstance(application);
        dao = db.mobileOrderDAO();
        mobileOrders = dao.getMobileOrders();
    }

    public LiveData<List<MobileOrder>> getAllMobileOrders(){
        return mobileOrders;
    }

    public void insert(MobileOrder mobileOrder){
        new Insert(this.dao).execute(mobileOrder);
    }

    private static class Insert extends AsyncTask<MobileOrder, Void, Void> {

        private MobileOrderDAO dao;

        Insert(MobileOrderDAO dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final MobileOrder... params){
            dao.insert(params[0]);
            return null;
        }
    }
}
