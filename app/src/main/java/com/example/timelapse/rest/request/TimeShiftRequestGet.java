package com.example.timelapse.rest.request;

import com.example.timelapse.object.WorkShift;
import com.example.timelapse.rest.request.core.GetRequest;

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
