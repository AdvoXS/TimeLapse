package com.example.timelapse.view;

import com.example.timelapse.object.WorkCalendar;

public interface CalendarView {
    void visibleElementsForWork(boolean t);

    void putInfoElementsDayOff(WorkCalendar calendar);

    void putInfoElementsWorkDay(WorkCalendar calendar);
}
