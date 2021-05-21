package com.example.timelapse.system.setting;

public enum DbAppSettings {
    TIMER_OBSERVE_CALENDAR("TIMER_OBSERVE_CALENDAR"),
    PREFIX_DAY_COLOR_BACKGROUND("BACK_COLOR_DAY_");

    private final String subscription;

    DbAppSettings(String subscription) {
        this.subscription = subscription;
    }

    public String getSettingName() {
        return subscription;
    }
}
