package com.example.timelapse.rest.request;

import com.example.timelapse.object.WorkShift;

import org.junit.Assert;
import org.junit.Test;

public class TimeShiftRequestGetTest {

    @Test
    public void getSubPath() {
        WorkShift[] workShifts = new TimeShiftRequestGet().execute();
        Assert.assertNotNull(workShifts);
    }
}