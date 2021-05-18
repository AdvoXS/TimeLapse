package com.example.timelapse.doctor;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBHelper;
import com.example.timelapse.object.template.DayBinderColorCreator;
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

    @Test
    public void initBindBackColorsDay() {
        DayBinderColorCreator.create(appContext);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("com.example.timelapse", appContext.getPackageName());
    }
}
