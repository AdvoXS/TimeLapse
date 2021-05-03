package com.example.timelapse.object;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class WorkCalendarWithShift {
    @Embedded
    public WorkCalendar workCalendar;

    @Relation(parentColumn = "id", entityColumn = "calendarId", entity = WorkShift.class)
    public List<WorkShift> workShifts;

    public WorkCalendar getWorkCalendar() {
        return workCalendar;
    }

    public void setWorkCalendar(WorkCalendar workCalendar) {
        this.workCalendar = workCalendar;
    }

    public List<WorkShift> getWorkShifts() {
        return workShifts;
    }

    public void setWorkShifts(List<WorkShift> workShifts) {
        this.workShifts = workShifts;
    }
}
