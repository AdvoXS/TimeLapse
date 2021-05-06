package com.example.timelapse.db.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.timelapse.object.WorkCalendarWithShift;

import java.util.Date;
import java.util.List;

@Dao
public interface WorkCalendarWithShiftDao {
    @Transaction
    @Query("SELECT * FROM WorkCalendar")
    public List<WorkCalendarWithShift> getAll();

    @Transaction
    @Query("SELECT * FROM WorkCalendar where id = :id")
    public WorkCalendarWithShift getById(long id);

    @Transaction
    @Query("SELECT * FROM WorkCalendar where date = :date")
    public WorkCalendarWithShift getByDate(Date date);

}
