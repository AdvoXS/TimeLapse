package com.example.timelapse.object;

import android.content.Context;
import android.os.SystemClock;

import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBHelper;
import com.example.timelapse.object.settings.AppSettings;
import com.example.timelapse.system.setting.DbAppSettings;
import com.example.timelapse.system.util.thread.AsyncCallVoid;

import java.util.ArrayList;
import java.util.List;

import static com.example.timelapse.system.setting.DbAppSettings.PREFIX_DAY_COLOR_BACKGROUND;

public class CreateDatabase {
    public static void create(Context context, boolean isRecreate) {
        AbstractDataBase db = DBHelper.getDB(context, DBHelper.LOCAL_BASE);
        if (isRecreate) {
            new AsyncCallVoid() {
                @Override
                protected Void run() {
                    db.notificationHistoryDao().deleteAll();
                    db.appSettingsDao().deleteAll();
                    return null;
                }
            }.execute();
        }
        new AsyncCallVoid() {
            @Override
            protected Void run() {
                List<AppSettings> settings = new ArrayList<>();
                settings.add(new AppSettings(PREFIX_DAY_COLOR_BACKGROUND.getSettingName() + "WORK", "#03DAC6"));
                settings.add(new AppSettings(PREFIX_DAY_COLOR_BACKGROUND.getSettingName() + "VACATION", "#3700B3"));
                settings.add(new AppSettings(PREFIX_DAY_COLOR_BACKGROUND.getSettingName() + "SICK_LEAVE", "#6200EE"));
                settings.add(new AppSettings(PREFIX_DAY_COLOR_BACKGROUND.getSettingName() + "DAY_OFF", "#FFFFFF"));
                settings.add(new AppSettings(PREFIX_DAY_COLOR_BACKGROUND.getSettingName() + "NON_WORKING", "#FFFFFF"));
                settings.add(new AppSettings(PREFIX_DAY_COLOR_BACKGROUND.getSettingName() + "HOLIDAY", "#FFFFFF"));
                settings.add(new AppSettings(DbAppSettings.TIMER_OBSERVE_CALENDAR.getSettingName(), "1000000"));
                db.appSettingsDao().insert(settings);
                return null;
            }
        }.execute();
        new AsyncCallVoid() {
            @Override
            protected Void run() {

                return null;
            }
        }.execute();
        SystemClock.sleep(5000);
    }
}
