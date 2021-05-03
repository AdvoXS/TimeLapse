package com.example.timelapse.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.timelapse.object.WorkCalendar;

import java.util.List;

@Dao
public interface WorkCalendarDao {

    @Query("SELECT * FROM WorkCalendar")
    List<WorkCalendar> getAll();

    @Query("SELECT * FROM WorkCalendar WHERE id IN (:ids)")
    List<WorkCalendar> loadAllByIds(int[] ids);

    @Insert
    void insert(WorkCalendar workCalendar);

    @Delete
    void delete(WorkCalendar workCalendar);
}
