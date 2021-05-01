package com.example.timelapse.object.template;

import com.example.timelapse.object.DayType;
import com.example.timelapse.object.Person;
import com.example.timelapse.object.WorkCalendar;
import com.example.timelapse.object.WorkShift;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkCalendarCreator {
    public static List<WorkCalendar> getWorkCalendarList() {
        List<WorkCalendar> workCalendars = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            Date date = new Date(121, 4, i);
            long id = i - 2;
            List<WorkShift> setShifts = new ArrayList<>();
            WorkShift shift = new WorkShift((long) i - 2, new Person(), Time.valueOf("08:00:00"), Time.valueOf("17:00:00"), DayType.WORK);
            setShifts.add(shift);
            workCalendars.add(new WorkCalendar(id, date, setShifts));
        }
        for (int i = 9; i < 14; i++) {
            Date date = new Date(121, 4, i);
            long id = i - 2;
            List<WorkShift> setShifts = new ArrayList<>();
            WorkShift shift = new WorkShift((long) i - 2, new Person(), Time.valueOf("08:00:00"), Time.valueOf("17:00:00"), DayType.WORK);
            setShifts.add(shift);
            workCalendars.add(new WorkCalendar(id, date, setShifts));
        }
        return workCalendars;
    }
}
