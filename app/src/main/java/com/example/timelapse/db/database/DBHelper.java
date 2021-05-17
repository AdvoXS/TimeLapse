package com.example.timelapse.db.database;

import android.content.Context;

import androidx.room.Room;

public class DBHelper {

    public final static String LOCAL_BASE = "local-base";
    public final static String ONLINE_BASE = "online-base";

    public static String CURRENT_DB = null;

    private static AbstractDataBase instance = null;

    public static AbstractDataBase getDB(Context context, String dbName) {
        if (instance == null)
            applyDB(context, dbName);
        else if (!CURRENT_DB.equals(dbName))
            applyDB(context, dbName);

        return instance;
    }

    private static void applyDB(Context context, String dbName) {
        instance = Room.databaseBuilder(context,
                LocalBase.class, dbName).build();
        CURRENT_DB = dbName;
    }
}
