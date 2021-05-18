package com.example.timelapse.rest.get;

import com.example.timelapse.object.WorkShift;
import com.example.timelapse.system.rest.GetRequest;

public class TimeShiftRequestGet extends GetRequest<WorkShift[]> {
    @Override
    public Class<WorkShift[]> getRestClazz() {
        return WorkShift[].class;
    }

    @Override
    public String getSubPath() {
        return "/1/shift";
    }
}
