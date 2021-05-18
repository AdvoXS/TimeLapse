package com.example.timelapse.db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.timelapse.object.Person;
import com.example.timelapse.object.WorkCalendar;
import com.example.timelapse.object.WorkShift;
import com.example.timelapse.object.settings.AppSettings;

import java.io.Serializable;

@Database(entities = {Person.class, WorkCalendar.class, WorkShift.class, AppSettings.class}, version = 3)
@TypeConverters({DBConverter.class})
public abstract class LocalBase extends RoomDatabase implements AbstractDataBase, Serializable {

}
