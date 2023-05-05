package com.example.mystandardapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PackageDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "package.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "packages";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATA = "data";
    private static final String COLUMN_SMS = "sms";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_DATAID = "dataid";
    private static final String COLUMN_SMSID = "smsid";

    public PackageDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATA + " TEXT, " +
                COLUMN_SMS + " TEXT," +
                COLUMN_PHONE + " TEXT," +
                COLUMN_SMSID + " TEXT," +
                COLUMN_DATAID + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Package getPackage(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_DATA, COLUMN_SMS, COLUMN_PHONE, COLUMN_SMSID, COLUMN_DATAID}, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            Package pack = new Package(Integer.parseInt(cursor.getString(0)), cursor.getInt(5), cursor.getInt(4), cursor.getString(3), cursor.getString(1), cursor.getString(2));
            db.close();
            cursor.close();
            return pack;
        }
        db.close();
        return null;
    }


    public boolean insertData(String data, String sms, String number, int smsId, int dataId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DATA, data);
        contentValues.put(COLUMN_SMS, sms);
        contentValues.put(COLUMN_PHONE, number);
        contentValues.put(COLUMN_SMSID, smsId);
        contentValues.put(COLUMN_DATAID, dataId);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public void updatePackage(Package pack, long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATA, pack.getDataPackageText());
        values.put(COLUMN_SMS, pack.getSmsPackageText());
        values.put(COLUMN_SMSID, pack.getSmsPackage());
        values.put(COLUMN_DATAID, pack.getSmsPackage());
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void deletePackage(long packageId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(packageId)});
        db.close();
    }
}
