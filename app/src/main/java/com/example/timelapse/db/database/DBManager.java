package com.example.timelapse.db.database;

import android.content.Context;

import androidx.room.Room;

public class DBManager {

    public static String LOCAL_BASE = "local-base";
    public static String ONLINE_BASE = "online-base";

    private static AbstractDataBase instance = null;

    public static AbstractDataBase getDB(Context context, String dbName) {
        if (instance == null) instance = Room.databaseBuilder(context,
                LocalBase.class, dbName).build();
        return instance;
    }
}
