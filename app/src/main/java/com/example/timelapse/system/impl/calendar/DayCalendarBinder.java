package com.example.timelapse.system.impl.calendar;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBHelper;
import com.example.timelapse.object.WorkCalendarWithShift;
import com.example.timelapse.object.settings.AppSettings;
import com.example.timelapse.system.util.thread.AsyncCallObject;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.model.DayOwner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DayCalendarBinder extends ContextWrapper {

    public DayCalendarBinder(Context base) {
        super(base);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void bind(List<WorkCalendarWithShift> calendarList, DayViewContainer dayViewContainer, CalendarDay calendarDay) {
        List<WorkCalendarWithShift> cloneCalendar = new ArrayList<>(calendarList);
        LocalDate today = LocalDate.now();
        if (calendarDay.getOwner().equals(DayOwner.THIS_MONTH)) {
            if (calendarDay.getDate().equals(today)) {
                dayViewContainer.textView.setBackgroundColor(Color.BLUE);
                dayViewContainer.textView.setTextColor(Color.WHITE);
            } else {
                Iterator<WorkCalendarWithShift> it = cloneCalendar.iterator();
                if (it.hasNext()) {
                    while (it.hasNext()) {
                        WorkCalendarWithShift workCalendar = it.next();
                        dayColorBind(dayViewContainer, workCalendar);
                        /*if (workCalendar.getWorkCalendar().getDate() != null && workCalendar.getWorkCalendar().getLocalDate().equals(calendarDay.getDate())) {
                            //dayColorBind(dayViewContainer, workCalendar);
                            dayViewContainer.textView.setTextColor(Color.WHITE);
                            dayViewContainer.textView.setBackgroundColor(Color.parseColor("#aa4ebaaa"));
                            it.remove();
                            break;
                        } else
                            dayViewContainer.textView.setTextColor(Color.BLACK);*/
                    }
                } else {
                    dayViewContainer.textView.setTextColor(Color.BLACK);
                }
            }
        } else
            dayViewContainer.textView.setTextColor(Color.GRAY);

    }

    private void dayColorBind(DayViewContainer dayViewContainer, WorkCalendarWithShift workCalendarWithShift) {
        AbstractDataBase db = DBHelper.getDB(getApplicationContext(), DBHelper.LOCAL_BASE);
        new AsyncCallObject<AppSettings>() {

            @Override
            protected AppSettings run() {
                return db.appSettingsDao().getByName("BACK_COLOR_DAY_" + workCalendarWithShift.getWorkShift().getDayType().name());
            }

            @Override
            protected void postExecute(AppSettings object) {
                super.postExecute(object);
                if (object != null) {
                    dayViewContainer.textView.setTextColor(Color.WHITE);
                    dayViewContainer.textView.setBackgroundColor(Color.parseColor(object.getSettingValue()));
                } else {
                    dayViewContainer.textView.setTextColor(Color.WHITE);
                    dayViewContainer.textView.setBackgroundColor(Color.parseColor("#aa4ebaaa"));
                }
            }
        }.execute();
    }
}
