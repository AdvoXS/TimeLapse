package com.example.timelapse.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.timelapse.object.Person;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM Person")
    List<Person> getAll();

    @Query("SELECT * FROM Person WHERE personId IN (:ids)")
    List<Person> loadAllByIds(int[] ids);

    @Insert
    void insert(Person person);

    @Delete
    void delete(Person person);
}
