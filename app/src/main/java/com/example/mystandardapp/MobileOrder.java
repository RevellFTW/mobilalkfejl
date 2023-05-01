package com.example.mystandardapp;

import androidx.room.Entity;


@Entity(tableName = "mobile_order")
public class MobileOrder {
    private Enums.DataOptions dataOption;
    private Enums.SmsOptions smsOption;

    public MobileOrder(Enums.DataOptions dataOption, Enums.SmsOptions smsOption) {
        this.dataOption = dataOption;
        this.smsOption = smsOption;
    }

    public Enums.DataOptions getDataOption() {
        return dataOption;
    }

    public void setDataOption(Enums.DataOptions dataOption) {
        this.dataOption = dataOption;
    }

    public Enums.SmsOptions getSmsOption() {
        return smsOption;
    }

    public void setSmsOption(Enums.SmsOptions smsOption) {
        this.smsOption = smsOption;
    }
}
