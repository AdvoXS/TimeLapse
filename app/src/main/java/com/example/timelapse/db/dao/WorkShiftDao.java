package com.example.timelapse.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.timelapse.object.WorkShift;

import java.util.List;

@Dao
public interface WorkShiftDao {
    @Query("SELECT * FROM WorkShift")
    List<WorkShift> getAll();

    @Query("SELECT * FROM WorkShift WHERE id IN (:ids)")
    List<WorkShift> loadAllByIds(int[] ids);

    @Query("SELECT * FROM WorkShift WHERE calendarId =:calendarIds")
    List<WorkShift> getFromParentId(long calendarIds);

    @Insert
    void insert(WorkShift workShift);

    @Update
    void update(WorkShift workShift);

    @Delete
    void delete(WorkShift workShift);

    @Transaction
    @Query("DELETE FROM WorkShift")
    void delete();
}
