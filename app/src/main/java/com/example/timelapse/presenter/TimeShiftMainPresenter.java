package com.example.timelapse.presenter;

import android.app.Activity;
import android.content.Intent;

import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBManager;
import com.example.timelapse.object.DayType;
import com.example.timelapse.object.WorkCalendarWithShift;
import com.example.timelapse.system.util.thread.AsyncCallObject;
import com.example.timelapse.view.TimeShiftMainView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class TimeShiftMainPresenter {

    private TimeShiftMainView timeShiftMainView;

    private AbstractDataBase db;

    public TimeShiftMainPresenter(TimeShiftMainView timeShiftMainView, Activity activity) {
        this.timeShiftMainView = timeShiftMainView;
        db = DBManager.getDB(activity.getApplicationContext(), DBManager.LOCAL_BASE);
    }

    public void fillElements(Intent intent) {
        LocalDate date = (LocalDate) intent.getSerializableExtra("DATE");
        Gson gs = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create();
        Type listType = new TypeToken<List<WorkCalendarWithShift>>() {
        }.getType();
        new AsyncCallObject<List<WorkCalendarWithShift>>() {

            @Override
            protected List<WorkCalendarWithShift> run() {
                return db.workCalendarWithShiftDao().getAll();
            }

            @Override
            protected void postExecute(List<WorkCalendarWithShift> object) {
                super.postExecute(object);
                boolean isExist = false;
                for (WorkCalendarWithShift workCalendar : object) {
                    if (workCalendar.getWorkCalendar().getLocalDate().equals(date)) {
                        if (!workCalendar.getWorkShift().getDayType().equals(DayType.WORK)) {
                            timeShiftMainView.visibleElementsForWork(false);
                            timeShiftMainView.putInfoElementsDayOff(workCalendar);
                        } else {
                            timeShiftMainView.visibleElementsForWork(true);
                            timeShiftMainView.putInfoElementsWorkDay(workCalendar);
                        }
                        isExist = true;
                        break;
                    }
                }

                if (!isExist) {
                    timeShiftMainView.visibleElementsForWork(false);
                    timeShiftMainView.putInfoElementsDayOff(new WorkCalendarWithShift());
                }
                timeShiftMainView.fillDate();
            }
        }.execute();

    }
}
