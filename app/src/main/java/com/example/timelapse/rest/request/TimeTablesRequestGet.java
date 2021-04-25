package com.example.timelapse.rest.request;

import com.example.timelapse.rest.object.template.TimeTable;

public class TimeTablesRequestGet extends GetRequest<TimeTable[]> {


    @Override
    public String getSubPath() {
        return "";
    }

    @Override
    public Class<TimeTable[]> getRestClazz() {
        return TimeTable[].class;
    }

    @Override
    public void postExecute(TimeTable[] timeTable) {
        System.out.println(timeTable[0].getId().toString());
        System.out.println(timeTable[0].getStatus());
    }
}
