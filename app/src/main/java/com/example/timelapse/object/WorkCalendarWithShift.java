package com.example.timelapse.object;

import androidx.room.Embedded;
import androidx.room.Relation;

public class WorkCalendarWithShift {
    @Embedded
    private WorkCalendar workCalendar;

    @Relation(parentColumn = "id", entityColumn = "calendarId", entity = WorkShift.class)
    private WorkShift workShift;

    public WorkCalendarWithShift(WorkCalendar workCalendar, WorkShift workShift) {
        this.workCalendar = workCalendar;
        this.workShift = workShift;
    }

    public WorkCalendarWithShift() {
    }

    public WorkCalendar getWorkCalendar() {
        return workCalendar;
    }

    public void setWorkCalendar(WorkCalendar workCalendar) {
        this.workCalendar = workCalendar;
    }

    public WorkShift getWorkShift() {
        return workShift;
    }

    public void setWorkShift(WorkShift workShift) {
        this.workShift = workShift;
    }
}
