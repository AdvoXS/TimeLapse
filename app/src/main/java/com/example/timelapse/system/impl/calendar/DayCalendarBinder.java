package com.example.timelapse.system.impl.calendar;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.timelapse.object.WorkCalendarWithShift;
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
                        if (workCalendar.getWorkCalendar().getDate() != null && workCalendar.getWorkCalendar().getLocalDate().equals(calendarDay.getDate())) {
                            //dayColorBind(dayViewContainer, workCalendar);
                            dayViewContainer.textView.setTextColor(Color.WHITE);
                            dayViewContainer.textView.setBackgroundColor(Color.parseColor("#aa4ebaaa"));
                            it.remove();
                            break;
                        } else
                            dayViewContainer.textView.setTextColor(Color.BLACK);
                    }
                } else {
                    dayViewContainer.textView.setTextColor(Color.BLACK);
                }
            }
        } else
            dayViewContainer.textView.setTextColor(Color.GRAY);

    }

    /*private void dayColorBind(DayViewContainer dayViewContainer, WorkCalendarWithShift workCalendarWithShift) {
        AbstractDataBase db = DBManager.getDB(getApplicationContext(), DBManager.LOCAL_BASE);
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

    }*/
}
