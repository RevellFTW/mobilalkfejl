package com.example.mystandardapp;

public class Package {


    public int id;
    public int dataPackage;
    public String dataPackageText;
    public int smsPackage;
    public String smsPackageText;

    public String mobileNumber;
    public String getDataPackageText() {
        return dataPackageText;
    }

    public void setDataPackageText(String dataPackageText) {
        this.dataPackageText = dataPackageText;
    }

    public String getSmsPackageText() {
        return smsPackageText;
    }

    public void setSmsPackageText(String smsPackageText) {
        this.smsPackageText = smsPackageText;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }



    public Package() {
    }

    public Package(int data, int sms, String number, String smsPackageText, String dataPackageText) {
        this.dataPackage = data;
        this.smsPackage = sms;
        this.mobileNumber = number;
        this.dataPackageText = dataPackageText;
        this.smsPackageText = smsPackageText;
    }

    public Package(int id, int data, int sms, String number,  String smsPackageText, String dataPackageText) {
        this.id = id;
        this.dataPackage = data;
        this.smsPackage = sms;
        this.mobileNumber = number;
        this.dataPackageText = dataPackageText;
        this.smsPackageText = smsPackageText;
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
