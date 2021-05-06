package com.example.timelapse.view;

import com.example.timelapse.object.WorkCalendarWithShift;

public interface TimeShiftMainView {
    void visibleElementsForWork(boolean t);

    void putInfoElementsDayOff(WorkCalendarWithShift calendar);

    void putInfoElementsWorkDay(WorkCalendarWithShift calendar);

    void fillDate();

}
