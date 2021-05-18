package com.example.timelapse.service;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.example.timelapse.activity.MainActivity;
import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBHelper;
import com.example.timelapse.object.WorkCalendar;
import com.example.timelapse.object.WorkShift;
import com.example.timelapse.rest.get.TimeShiftRequestGet;
import com.example.timelapse.rest.get.WorkCalendarRequestGet;

public class ObserveTimeLapseService extends JobIntentService {
    public static int JOB_ID = 1000;
    private final Object lock = new Object();

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, ObserveTimeLapseService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        synchronized (lock) {
            AbstractDataBase db = DBHelper.getDB(getApplicationContext(), DBHelper.LOCAL_BASE);
            WorkShift[] workShifts;
            WorkCalendar[] workCalendars;
            while (true) {
                workShifts = new TimeShiftRequestGet().execute();
                workCalendars = new WorkCalendarRequestGet().execute();
                for (WorkCalendar workCalendar : workCalendars) {
                    db.workCalendarDao().insert(workCalendar);
                }
                for (WorkShift workShift : workShifts) {
                    db.workShiftDao().insert(workShift);
                }
                workShifts = null;
                workCalendars = null;
                Intent broadIntent = new Intent(MainActivity.BROADCAST_OBSERVE);
                sendBroadcast(broadIntent);
                SystemClock.sleep(1000000);
            }
        }
    }


}
