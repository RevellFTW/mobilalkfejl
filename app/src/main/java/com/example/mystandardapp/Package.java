package com.example.mystandardapp;

public class Package {


    public int id;
    public int dataPackage;
    public int smsPackage;

    public Package() {
    }

    public Package(int data, int sms) {
        this.dataPackage = data;
        this.smsPackage = sms;
    }

    public Package(int id, int data, int sms) {
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

    public int getDataPackage() {
        return dataPackage;
    }

    public void setDataPackage(int dataPackage) {
        this.dataPackage = dataPackage;
    }

    public int getSmsPackage() {
        return smsPackage;
    }

    public void setSmsPackage(int smsPackage) {
        this.smsPackage = smsPackage;
    }
}
