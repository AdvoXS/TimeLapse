package com.example.timelapse.object.settings;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AppSettings {
    @PrimaryKey
    private long id;

    private String settingName;

    private String settingValue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }
}
