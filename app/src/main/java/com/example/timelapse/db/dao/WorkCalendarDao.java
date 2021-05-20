package com.example.timelapse.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.timelapse.object.WorkCalendar;

import java.util.Date;
import java.util.List;

@Dao
public interface WorkCalendarDao {

    @Query("SELECT * FROM WorkCalendar")
    List<WorkCalendar> getAll();

    @Query("SELECT * FROM WorkCalendar WHERE id IN (:ids)")
    List<WorkCalendar> loadAllByIds(int[] ids);

    @Query("SELECT * FROM WorkCalendar WHERE date =:date")
    WorkCalendar getFromDate(Date date);

    @Query("SELECT * FROM WorkCalendar WHERE id =:id")
    WorkCalendar getById(long id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WorkCalendar workCalendar);

    @Update
    void update(WorkCalendar workCalendar);

    @Delete
    void delete(WorkCalendar workCalendar);

    @Transaction
    @Query("DELETE FROM WorkCalendar")
    void delete();
}
