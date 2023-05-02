package com.example.mystandardapp;

public class Package {


    public int id;
    public String dataPackage;
    public String smsPackage;

    public Package() {
    }

    public Package(String data, String sms) {
        this.dataPackage = data;
        this.smsPackage = sms;
    }

    public Package(int id, String data, String sms) {
        this.id = id;
        this.dataPackage = data;
        this.smsPackage = sms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataPackage() {
        return dataPackage;
    }

    public void setDataPackage(String dataPackage) {
        this.dataPackage = dataPackage;
    }

    public String getSmsPackage() {
        return smsPackage;
    }

    public void setSmsPackage(String smsPackage) {
        this.smsPackage = smsPackage;
    }
}
