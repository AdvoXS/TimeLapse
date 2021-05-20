package com.example.timelapse.db.database;


import com.example.timelapse.db.dao.AppSettingsDao;
import com.example.timelapse.db.dao.NotificationHistoryDao;
import com.example.timelapse.db.dao.PersonDao;
import com.example.timelapse.db.dao.WorkCalendarDao;
import com.example.timelapse.db.dao.WorkCalendarWithShiftDao;
import com.example.timelapse.db.dao.WorkShiftDao;

public interface AbstractDataBase {
    PersonDao personDao();

    WorkCalendarDao workCalendarDao();

    WorkShiftDao workShiftDao();

    WorkCalendarWithShiftDao workCalendarWithShiftDao();

    AppSettingsDao appSettingsDao();

    NotificationHistoryDao notificationHistoryDao();
}
