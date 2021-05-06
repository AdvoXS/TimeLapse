package com.example.timelapse.system.util;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBManager;
import com.example.timelapse.object.WorkCalendar;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.regex.Pattern;

public class DateToDisplayStringTest {
    @BeforeClass
    public static void setup() {
        // Setting up once before all tests
    }

    @Test
    public void test() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AbstractDataBase db = DBManager.getDB(appContext, DBManager.LOCAL_BASE);
        for (WorkCalendar calendar : db.workCalendarDao().getAll()) {
            String displayDate = DateUtils.dateToDisplayString(calendar.getDate());
            Assert.assertTrue(Pattern.compile("\\d{1,2} [а-яА-Я]+ \\d\\d\\d\\d").matcher(displayDate).find());
        }
    }
}