package com.example.mystandardapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MobileOrderDAO {
    @Insert
    void insert(MobileOrder mobileOrder);

    @Query("DELETE FROM mobile_order")
    void deleteAll();

    //@Delete
    //void delete(MobileOrder mobileOrder);

    @Query("SELECT * FROM mobile_order ORDER BY dataOption ASC")
    LiveData<List<MobileOrder>> getMobileOrders();
}
