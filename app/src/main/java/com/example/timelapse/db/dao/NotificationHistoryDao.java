package com.example.timelapse.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.timelapse.object.NotificationHistory;

import java.util.List;

@Dao
public interface NotificationHistoryDao {
    @Query("SELECT * FROM NotificationHistory")
    List<NotificationHistory> getAll();

    @Query("SELECT * FROM NotificationHistory WHERE id=:id")
    NotificationHistory getById(Long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NotificationHistory notificationHistory);

    @Delete
    void delete(NotificationHistory notificationHistory);

    @Transaction
    @Query("DELETE FROM NotificationHistory")
    void deleteAll();

    @Update
    void update(NotificationHistory notificationHistory);
}
