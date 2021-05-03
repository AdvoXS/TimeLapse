package com.example.timelapse.system.impl;

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

public class DayCalendarBinder {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void bind(List<WorkCalendarWithShift> calendarList, DayViewContainer dayViewContainer, CalendarDay calendarDay) {
        List<WorkCalendarWithShift> cloneCalendar = new ArrayList<>(calendarList);
        //cloneCalendar.sort(Comparator.comparing(WorkCalendar::getLocalDate));
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
                        if (workCalendar.workCalendar.getLocalDate().equals(calendarDay.getDate())) {
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
}
