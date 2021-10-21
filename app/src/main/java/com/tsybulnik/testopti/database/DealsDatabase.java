package com.tsybulnik.testopti.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tsybulnik.testopti.model.Deal;
@Database(entities = {Deal.class},version = 1,exportSchema = false)
public abstract class DealsDatabase extends RoomDatabase {
    private static DealsDatabase database;
    public static final String DB_NAME = "deals.db";
    public static final Object LOCK = new Object();


    public static DealsDatabase newInstance(Context context){
        synchronized (LOCK){
            if(database==null){
                database = Room.databaseBuilder(context,DealsDatabase.class,DB_NAME).allowMainThreadQueries().build();
            }
            return database;
        }

    }
    public abstract DealDao dealDao();
}
