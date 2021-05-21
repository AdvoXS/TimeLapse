package com.example.timelapse.object.settings;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AppSettings {
    @PrimaryKey
    @NonNull
    private String settingName;

    private String settingValue;

    public AppSettings(@NonNull String settingName, String settingValue) {
        this.settingName = settingName;
        this.settingValue = settingValue;
    }

    @NonNull
    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(@NonNull String settingName) {
        this.settingName = settingName;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }
}
