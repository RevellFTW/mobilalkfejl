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

    public PackageDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATA + " TEXT, " +
                COLUMN_SMS + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Package getPackage(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_ID, COLUMN_DATA, COLUMN_SMS }, COLUMN_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            Package pack =  new Package(Integer.parseInt(cursor.getString(0)), cursor.getInt(1), cursor.getInt(2));
            cursor.close();
            return pack;
        }
        return null;
    }



    public boolean insertData(int data, int sms) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DATA, data);
        contentValues.put(COLUMN_SMS, sms);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }

    }
    public void updatePackage(Package pack, long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATA, pack.getDataPackage());
        values.put(COLUMN_SMS, pack.getSmsPackage());
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[] { String.valueOf(pack.getId()) });
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
                new String[] { String.valueOf(packageId) });
        db.close();
    }
}
