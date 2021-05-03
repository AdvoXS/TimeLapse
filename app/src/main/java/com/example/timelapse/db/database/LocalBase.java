package com.example.timelapse.db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.timelapse.object.Person;
import com.example.timelapse.object.WorkCalendar;
import com.example.timelapse.object.WorkShift;

@Database(entities = {Person.class, WorkCalendar.class, WorkShift.class}, version = 1)
@TypeConverters({DBConverter.class})
public abstract class LocalBase extends RoomDatabase implements AbstractDataBase {

}
