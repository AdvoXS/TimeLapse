package com.example.timelapse.service;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.example.timelapse.activity.fragment.CalendarFragment;
import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBHelper;
import com.example.timelapse.object.DayType;
import com.example.timelapse.object.NotificationHistory;
import com.example.timelapse.object.WorkCalendar;
import com.example.timelapse.object.WorkShift;
import com.example.timelapse.object.settings.AppSettings;
import com.example.timelapse.rest.get.TimeShiftRequestGet;
import com.example.timelapse.rest.get.WorkCalendarRequestGet;
import com.example.timelapse.system.setting.DbAppSettings;
import com.example.timelapse.system.util.DateUtils;

import java.util.Date;

public class ObserveTimeLapseService extends JobIntentService {
    public static int JOB_ID = 1000;
    private final Object lock = new Object();
    AbstractDataBase db;
    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, ObserveTimeLapseService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        synchronized (lock) {
            db = DBHelper.getDB(getApplicationContext(), DBHelper.LOCAL_BASE);
            AppSettings timerSetting = db.appSettingsDao().getByName(DbAppSettings.TIMER_OBSERVE_CALENDAR.getSettingName());
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
                if (workShifts != null && workShifts.length != 0) {
                    createNotificationDefault(workShifts);
                }
                workShifts = null;
                workCalendars = null;
                Intent broadIntent = new Intent(CalendarFragment.BROADCAST_OBSERVE);
                sendBroadcast(broadIntent);
                SystemClock.sleep(Long.parseLong(timerSetting.getSettingValue()));
            }
        }
    }

    private void createNotificationDefault(WorkShift[] workShifts) {
        NotificationHistory notificationHistory = new NotificationHistory();
        notificationHistory.setDate(new Date());

        StringBuilder descrBuilder = new StringBuilder();
        StringBuilder shortDescrBuilder = new StringBuilder();
        for (WorkShift workShift : workShifts) {
            WorkCalendar calendar = db.workCalendarDao().getById(workShift.getCalendarId());
            shortDescrBuilder.append(DateUtils.dateToDisplayString(calendar.getDate())).append(" ");
            descrBuilder.append("Изменение смены на дату: ").append(calendar.getDate()).append(" Тип дня: ").append(workShift.getDayType().getAbout());
            if (workShift.getDayType() != null && workShift.getDayType().equals(DayType.WORK)) {
                descrBuilder.append(" Начинается с ").append(workShift.getStartTime()).append(" По ").append(workShift.getEndTime()).append("\n");
            }
        }
        notificationHistory.setShortDescription("Изменение рабочего графика на даты: " + shortDescrBuilder.toString());
        notificationHistory.setDescription(descrBuilder.toString());
        db.notificationHistoryDao().insert(notificationHistory);
    }


}
