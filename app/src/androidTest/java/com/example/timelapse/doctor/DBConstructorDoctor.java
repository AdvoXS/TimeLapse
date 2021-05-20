package com.example.timelapse.doctor;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBHelper;
import com.example.timelapse.object.settings.AppSettings;
import com.example.timelapse.system.impl.calendar.DayCalendarBinder;
import com.example.timelapse.system.util.thread.AsyncCallVoid;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DBConstructorDoctor {
    Context appContext;

    @Before
    public void init() {
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void create() {
        AbstractDataBase db = DBHelper.getDB(appContext, DBHelper.LOCAL_BASE);
        new AsyncCallVoid() {
            @Override
            protected Void run() {
                AppSettings settingColorDayWork = new AppSettings(DayCalendarBinder.PREFIX_COLOR_DAY + "WORK", "#03DAC6");
                AppSettings settingColorDayVacation = new AppSettings(DayCalendarBinder.PREFIX_COLOR_DAY + "VACATION", "#3700B3");
                AppSettings settingColorDaySeackLeave = new AppSettings(DayCalendarBinder.PREFIX_COLOR_DAY + "SICK_LEAVE", "#6200EE");
                AppSettings settingColorDay1 = new AppSettings(DayCalendarBinder.PREFIX_COLOR_DAY + "DAY_OFF", "#FFFFFF");
                AppSettings settingColorDayS2 = new AppSettings(DayCalendarBinder.PREFIX_COLOR_DAY + "NON_WORKING", "#FFFFFF");
                AppSettings settingColorDay3 = new AppSettings(DayCalendarBinder.PREFIX_COLOR_DAY + "HOLIDAY", "#FFFFFF");
                db.appSettingsDao().insert(settingColorDayWork);
                db.appSettingsDao().insert(settingColorDayVacation);
                db.appSettingsDao().insert(settingColorDay1);
                db.appSettingsDao().insert(settingColorDayS2);
                db.appSettingsDao().insert(settingColorDay3);
                db.appSettingsDao().insert(settingColorDaySeackLeave);
                return null;
            }
        }.execute();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteAllAppSettings() {
        AbstractDataBase db = DBHelper.getDB(appContext, DBHelper.LOCAL_BASE);
        new AsyncCallVoid() {
            @Override
            protected Void run() {
                db.appSettingsDao().deleteAll();
                return null;
            }
        }.execute();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("com.example.timelapse", appContext.getPackageName());
    }
}
