package com.example.roomdb;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 3)
public abstract class DBHelper extends RoomDatabase {

    public abstract UserDao getDao();
    public static DBHelper instance;

    public static DBHelper getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context, DBHelper.class, "user_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
