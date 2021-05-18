package com.example.timelapse.rest.get;

import com.example.timelapse.object.WorkCalendar;
import com.example.timelapse.system.rest.GetRequest;

public class WorkCalendarRequestGet extends GetRequest<WorkCalendar[]> {


    @Override
    public String getSubPath() {
        return "";
    }

    @Override
    public Class<WorkCalendar[]> getRestClazz() {
        return WorkCalendar[].class;
    }

}
