package com.example.timelapse.rest.request;

import com.example.timelapse.object.WorkCalendar;
import com.example.timelapse.rest.request.core.GetRequest;

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
