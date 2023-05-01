package com.example.mystandardapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {MobileOrder.class}, version = 1, exportSchema = false)
public abstract class MobileOrderRoomDatabase extends RoomDatabase{
    public abstract MobileOrderDAO mobileOrderDAO();

    private static MobileOrderRoomDatabase INSTANCE;

    public static MobileOrderRoomDatabase getInstance(Context context){
        if(INSTANCE == null)
            synchronized (MobileOrderRoomDatabase.class){
                if(INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MobileOrderRoomDatabase.class, "mobile_order_database")
                            .fallbackToDestructiveMigration()
                            .build();
            }

        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            new InitDb((MobileOrderDAO) INSTANCE).execute();
        }
    };

    private static class InitDb extends AsyncTask<Void, Void, Void>{
        private MobileOrderDAO dao;
        InitDb(MobileOrderDAO dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.dao.deleteAll();

            MobileOrder mobileOrder = new MobileOrder(Enums.DataOptions.SMALL, Enums.SmsOptions.SMALL);
            this.dao.insert(mobileOrder);
            return null;
        }
    }
}
