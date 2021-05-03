package com.example.timelapse.db.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.timelapse.object.WorkCalendarWithShift;

import java.util.List;

@Dao
public interface WorkCalendarWithShiftDao {
    @Transaction
    @Query("SELECT * FROM WorkCalendar")
    public List<WorkCalendarWithShift> getAll();
}
