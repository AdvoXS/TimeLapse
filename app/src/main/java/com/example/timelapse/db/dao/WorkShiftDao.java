package com.example.timelapse.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.timelapse.object.WorkShift;

import java.util.List;

@Dao
public interface WorkShiftDao {
    @Query("SELECT * FROM WorkShift")
    List<WorkShift> getAll();

    @Query("SELECT * FROM WorkShift WHERE id IN (:ids)")
    List<WorkShift> loadAllByIds(int[] ids);

    @Insert
    void insert(WorkShift workShift);

    @Delete
    void delete(WorkShift workShift);
}
