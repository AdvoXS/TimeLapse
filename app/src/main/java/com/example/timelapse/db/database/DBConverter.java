package com.example.timelapse.db.database;

import androidx.room.TypeConverter;

import java.sql.Time;
import java.util.Date;

public class DBConverter {
    @TypeConverter
    public static Time fromTimestamp(Long value) {
        return value == null ? null : new Time(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}