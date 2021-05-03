package com.example.timelapse.presenter;

import android.content.Intent;

import com.example.timelapse.object.DayType;
import com.example.timelapse.object.WorkCalendarWithShift;
import com.example.timelapse.view.CalendarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDate;
import java.util.List;

public class CalendarPresenter {

    private CalendarView calendarView;

    public CalendarPresenter(CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    public void fillElements(Intent intent) {
        LocalDate date = (LocalDate) intent.getSerializableExtra("DATE");
        List<WorkCalendarWithShift> calendar = new Gson().fromJson(intent.getStringExtra("CALENDAR"), new TypeToken<List<WorkCalendarWithShift>>() {
        }.getType());
        boolean isExist = false;
        for (WorkCalendarWithShift workCalendar : calendar) {
            if (workCalendar.workCalendar.getLocalDate().equals(date)) {
                if (!workCalendar.workShifts.get(0).getDayType().equals(DayType.WORK)) {
                    calendarView.visibleElementsForWork(false);
                    calendarView.putInfoElementsDayOff(workCalendar);
                } else {
                    calendarView.visibleElementsForWork(true);
                    calendarView.putInfoElementsWorkDay(workCalendar);
                }
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            calendarView.visibleElementsForWork(false);
            calendarView.putInfoElementsDayOff(new WorkCalendarWithShift());
        }
    }
}
