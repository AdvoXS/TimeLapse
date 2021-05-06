package com.example.timelapse.db.dao;

import com.example.timelapse.object.settings.AppSettings;

import java.util.List;

public interface AppSettingsDao {
    //@Query("SELECT * FROM AppSettings")
    List<AppSettings> getAll();

    // @Query("SELECT * FROM AppSettings WHERE settingName=:name")
    AppSettings getByName(String name);

    //  @Insert
    void insert(AppSettings appSettings);

    // @Delete
    void delete(AppSettings appSettings);

    //  @Update
    void update(AppSettings appSettings);
}
